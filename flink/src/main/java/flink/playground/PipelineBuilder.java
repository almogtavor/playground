package flink.playground;

import flink.playground.model.ExampleData;
import lombok.SneakyThrows;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.springframework.stereotype.Component;
//import org.mongoflink.config.MongoOptions;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
public class PipelineBuilder {
    private KafkaSource<ExampleData> kafkaSource;

    public PipelineBuilder(KafkaSource<ExampleData> kafkaSource) {
        this.kafkaSource = kafkaSource;
    }

    @SneakyThrows
    public void flow() {

        final String mode = "ordered";
        final long timeout = 10000L;

        // obtain execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getCheckpointConfig().setCheckpointInterval(1000L);

        // create input stream of a single integer
        DataStream<ExampleData> inputStream = env.fromSource(kafkaSource, WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(20)), "Kafka Source");

        AsyncFunction<ExampleData, ExampleData> function = new SampleAsyncFunction();

        // add async operator to streaming job
        DataStream<ExampleData> result;
        switch (mode.toUpperCase()) {
            case "ORDERED":
                result =
                        AsyncDataStream.orderedWait(
                                inputStream, function, timeout, TimeUnit.MILLISECONDS, 20);
                break;
            case "UNORDERED":
                result =
                        AsyncDataStream.unorderedWait(
                                inputStream, function, timeout, TimeUnit.MILLISECONDS, 20);
                break;
            default:
                throw new IllegalStateException("Unknown mode: " + mode);
        }

        result.print();

        // execute the program
        env.execute("Async IO Example: " + mode);




        // if these rows are not multiple times of rps, there would be the records remaining not flushed
        // after the last checkpoint
        long rps = 50;
        long rows = 1000L;

        Properties properties = new Properties();
//        properties.setProperty(MongoOptions.SINK_TRANSACTION_ENABLED, "false");
//        properties.setProperty(MongoOptions.SINK_FLUSH_ON_CHECKPOINT, "false");
//        properties.setProperty(MongoOptions.SINK_FLUSH_SIZE, String.valueOf(1_000L));
//        properties.setProperty(MongoOptions.SINK_FLUSH_INTERVAL, String.valueOf(10_000L));
//
//        env.addSource(new DataGeneratorSource<>(new StringGenerator(), rps, rows))
//                .returns(String.class)
//                .sinkTo(new MongoSink<>(CONNECT_STRING, DATABASE_NAME, COLLECTION,
//                        new StringDocumentSerializer(), properties));
//        StreamGraph streamGraph = env.getStreamGraph();
//
//        final Configuration config = new Configuration();
//        config.setString(RestOptions.BIND_PORT, "18081-19000");
//        final MiniClusterConfiguration cfg =
//                new MiniClusterConfiguration.Builder()
//                        .setNumTaskManagers(1)
//                        .setNumSlotsPerTaskManager(4)
//                        .setConfiguration(config)
//                        .build();
//
//        try (MiniCluster miniCluster = new MiniCluster(cfg)) {
//            miniCluster.start();
//            miniCluster.executeJobBlocking(streamGraph.getJobGraph());
//        }
    }
}
