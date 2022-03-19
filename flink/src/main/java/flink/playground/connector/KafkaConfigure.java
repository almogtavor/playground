package flink.playground.connector;

import flink.playground.model.ExampleData;
import flink.playground.serialization.ExampleDataSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigure {
    @Bean
    public KafkaSource<ExampleData> kafkaSource() {
        return KafkaSource.<ExampleData>builder()
                .setBootstrapServers("brokers")
                .setTopics("input-topic")
                .setGroupId("my-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new ExampleDataSchema())
                .build();
    }
    @Bean
    public KafkaSink<String> kafkaSink() {
        return KafkaSink.<String>builder()
                .setBootstrapServers("brokers")
                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
                        .setTopic("topic-name")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build()
                )
                .setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();
    }
}
