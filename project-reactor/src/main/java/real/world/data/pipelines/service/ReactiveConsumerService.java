package real.world.data.pipelines.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


@Service
public class ReactiveConsumerService {
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    @Qualifier("directChannel")
    @Autowired
    public MessageChannel directChannel;

    public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    @Bean
    public IntegrationFlow readFromKafka() {
        return IntegrationFlows.from(reactiveKafkaConsumerTemplate.receiveAutoAck()
                .map(GenericMessage::new))
            .<ConsumerRecord<String, String>, String>transform(ConsumerRecord::value)
            .<String, String>transform(String::toUpperCase)
            .channel(directChannel)
            .get();
    }
}
