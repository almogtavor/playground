package flink.playground;

import lombok.SneakyThrows;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.minicluster.MiniCluster;
import org.apache.flink.runtime.minicluster.MiniClusterConfiguration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.datagen.DataGeneratorSource;
import org.apache.flink.streaming.api.graph.StreamGraph;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.springframework.stereotype.Component;
//import org.mongoflink.config.MongoOptions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


import static com.mongodb.internal.client.model.AggregationLevel.COLLECTION;

@Component
public class PipelineBuilder {
    @SneakyThrows
    public void flow() {

        final String mode = "ordered";
        final long timeout = 10000L;

        // obtain execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.getCheckpointConfig().setCheckpointInterval(1000L);

        // create input stream of a single integer
//        DataStream<Integer> inputStream = env.addSource(new SimpleSource());
//        env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");
//        FlinkKafkaConsumer<MyType> kafkaSource = new FlinkKafkaConsumer<>("myTopic", schema, props);
//        kafkaSource.assignTimestampsAndWatermarks(
//                WatermarkStrategy
//                        .forBoundedOutOfOrderness(Duration.ofSeconds(20)));
//
//        DataStream<MyType> stream = env.addSource(kafkaSource);

        AsyncFunction<Integer, String> function = new SampleAsyncFunction();

        // add async operator to streaming job
        DataStream<String> result;
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
