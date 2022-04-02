package reactor.kafka.spring.integration.samples.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.ReactiveS3MessageHandler;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.ReactiveSolrMessageHandler;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Component
@AllArgsConstructor
public class FlowBuilder extends IntegrationFlowAdapter {
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;
    public S3AsyncClient s3client;
    public String bucketName = "fajifjaie";

    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
//        TaskExecutor taskExecutor = new TaskExecutorAdapter()
        return from(reactiveKafkaConsumerTemplate.receiveAutoAck()
                        .map(GenericMessage::new))
            .<ConsumerRecord<String, String>, String>transform(ConsumerRecord::value)
                .<Message, String>transform(message -> message.getHeaders().getId())
                .<String, String>transform(String::toUpperCase)
            .publishSubscribeChannel(s -> s
                .subscribe(f -> f
                    .handle(new ReactiveS3MessageHandler(s3client, bucketName)))
                .subscribe(f -> f
                    .handle(new ReactiveSolrMessageHandler()))
            );
    }

    private IntegrationFlow bulkWriteToPulsar() {
        return null;
    }

    private IntegrationFlow bulkWriteToCockroach() {
        return null;
    }
}
