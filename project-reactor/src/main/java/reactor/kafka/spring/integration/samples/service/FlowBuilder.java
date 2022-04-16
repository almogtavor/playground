package reactor.kafka.spring.integration.samples.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.ReactiveS3MessageHandler;
import reactor.kafka.spring.integration.samples.model.ExampleData;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.io.DataInput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlowBuilder implements ApplicationRunner {
    public ReceiverOptions<String, String> receiverOptions;
    public S3AsyncClient s3client;
    public String bucketName = "fajifjaie";
    ObjectMapper mapper = new ObjectMapper();

    private Optional<?> getObject(ReceiverRecord<String, String> receiverRecord) {
        try {
            return Optional.of(mapper.readValue(receiverRecord.value(), ExampleData.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public AbstractReplyProducingMessageHandler fileWritingMessageHandler() {
        AbstractReplyProducingMessageHandler mh = new AbstractReplyProducingMessageHandler() {
            @Override
            protected Object handleRequestMessage(Message<?> message) {
                String payload = (String) message.getPayload();
                return "Message Payload : ".concat(payload);
            }
        };
        mh.setOutputChannelName("outputChannel");
        return mh;
    }

    private IntegrationFlow bulkWriteToPulsar() {
        return null;
    }

    private IntegrationFlow bulkWriteToCockroach() {
        return null;
    }

    @Override
    public void run(ApplicationArguments args) {
        Flux.from(KafkaReceiver.create(receiverOptions)
                .receive()
                .map(this::getObject)
                .mapNotNull(optionalObject -> MessageBuilder
                        .withPayload(optionalObject.orElseThrow())
                        .setHeader(MessageHeaders.ID, ((ExampleData) optionalObject.orElseThrow()).getItemId())
                        .build())
                .groupBy(message -> Objects.requireNonNull(message.getHeaders().get("ID")))
                .flatMap(objectMessageGroupedFlux -> objectMessageGroupedFlux
                        .sort(Comparator.comparing(obj -> ((ExampleData) (obj.getPayload())).getReceptionTime()))
                        .take(1))
                .doOnNext(message -> new ReactiveS3MessageHandler(s3client,
                        new LiteralExpression(String.format("meteor-prod-text3-%s", Objects.requireNonNull(message.getHeaders().getId()))))
                        .handleMessageInternal(message))
                .doOnNext(message -> new ReactiveMongoMessageHandler(s3client,
                        new LiteralExpression(String.format("meteor-prod-text3-%s", Objects.requireNonNull(message.getHeaders().getId()))))
                        .handleMessageInternal(message))
                .doOnNext(message -> new ReactiveKafkaMessageHandler(s3client,
        new LiteralExpression(String.format("meteor-prod-text3-%s", Objects.requireNonNull(message.getHeaders().getId()))))
                        .handleMessageInternal(message))
        ).subscribe();
    }
}
