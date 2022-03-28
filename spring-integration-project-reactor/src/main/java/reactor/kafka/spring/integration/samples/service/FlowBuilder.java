package reactor.kafka.spring.integration.samples.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class FlowBuilder extends IntegrationFlowAdapter {
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    public FlowBuilder(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }
    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        return from(reactiveKafkaConsumerTemplate.receiveAutoAck()
                        .map(GenericMessage::new))
            .<ConsumerRecord<String, String>, String>transform(ConsumerRecord::value)
            .<String, String>transform(String::toUpperCase)
            .publishSubscribeChannel(s -> s
                .subscribe(f -> f
                    .handle(bulkWriteToCockroach()))
                .subscribe(f -> f
                    .handle(bulkWriteToPulsar()))
                .get().setIgnoreFailures(false));
    }

    private IntegrationFlow bulkWriteToPulsar() {
        return null;
    }

    private IntegrationFlow bulkWriteToCockroach() {
        return null;
    }
}
