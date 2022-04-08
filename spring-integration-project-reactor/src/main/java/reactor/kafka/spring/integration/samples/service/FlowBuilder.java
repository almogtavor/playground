package reactor.kafka.spring.integration.samples.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.handler.ReactiveMessageHandlerAdapter;
import org.springframework.integration.http.dsl.Http;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.ReactiveS3MessageHandler;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.ReactiveSolrMessageHandler;
import reactor.kafka.spring.integration.samples.model.ExampleData;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlowBuilder extends IntegrationFlowAdapter {
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;
    public S3AsyncClient s3client;
    public String bucketName = "fajifjaie";

    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        Function<String, String> mapper = String::toUpperCase;

//        TaskExecutor taskExecutor = new TaskExecutorAdapter()
        return from(reactiveKafkaConsumerTemplate.receiveAutoAck()
                    .map(GenericMessage::new))
                .<ConsumerRecord<String, String>, String>transform(ConsumerRecord::value)
                .transform(Transformers.fromJson(ExampleData.class))
                .enrich(enricher -> enricher
                        .<Map<String, ?>>requestPayload(message ->
                                ((List<?>) message.getPayload().get("attributeIds"))
                                        .stream()
                                        .map(Object::toString)
                                        .collect(Collectors.joining(",")))
                        .requestSubFlow(subFlow ->
                                subFlow.handle(
                                        Http.outboundGateway("/attributes?id={ids}", restTemplate)
                                                .httpMethod(HttpMethod.GET)
                                                .expectedResponseType(Map.class)
                                                .uriVariable("ids", "payload")))
                        .propertyExpression("attributes", "payload.attributes"))
                .fluxTransform(messageFlux -> messageFlux.map(mapper))
                .handle(new ReactiveMessageHandlerAdapter(message -> {
                    if (message.getPayload() instanceof Flux) {
                        return (Mono<Void>) ((Flux) message.getPayload()).subscribe(System.out::println);
                    }
                    return null;
                } (System.out.println("fe"))))
//                .<Message, String>transform(message -> message.getHeaders().getId())
//                .<String, String>transform(String::toUpperCase)
//                .publishSubscribeChannel(s -> s
//                        .subscribe(f -> f
//                                .handle(new ReactiveS3MessageHandler(s3client, new LiteralExpression(bucketName))))
//                        .subscribe(f -> f
//                                .handle(new ReactiveSolrMessageHandler()))
//                );
    }

    private IntegrationFlow bulkWriteToPulsar() {
        return null;
    }

    private IntegrationFlow bulkWriteToCockroach() {
        return null;
    }
}
