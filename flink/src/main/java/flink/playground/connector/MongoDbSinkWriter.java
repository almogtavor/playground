//package flink.playground.connector;
//
//import com.mongodb.client.model.WriteModel;
//import flink.playground.async.sink.*;
//import flink.playground.async.sink.sink2.Sink;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.flink.metrics.Counter;
//import org.apache.flink.metrics.groups.SinkWriterMetricGroup;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Consumer;
//
//public class MongoDbSinkWriter<InputT> extends AsyncSinkWriter<InputT, MongoDbWriteRequest> {
//    private static final Logger LOG = LoggerFactory.getLogger(MongoDbSinkWriter.class);
//
//    /* A counter for the total number of records that have encountered an error during put */
//    private final Counter numRecordsOutErrorsCounter;
//
//    /* The sink writer metric group */
//    private final SinkWriterMetricGroup metrics;
//
//    private final DynamoDbTablesConfig tablesConfig;
//    private final DynamoDbAsyncClient client;
//    private final boolean failOnError;
//
//    public MongoDbSinkWriter(
//            ElementConverter<InputT, MongoDbWriteRequest> elementConverter,
//            Sink.InitContext context,
//            int maxBatchSize,
//            int maxInFlightRequests,
//            int maxBufferedRequests,
//            long maxBatchSizeInBytes,
//            long maxTimeInBufferMS,
//            long maxRecordSizeInBytes) {
//        super(elementConverter,
//                context,
//                maxBatchSize,
//                maxInFlightRequests,
//                maxBufferedRequests,
//                maxBatchSizeInBytes,
//                maxTimeInBufferMS,
//                maxRecordSizeInBytes);
//    }
//
//
//    @Override
//    protected void submitRequestEntries(
//            List<MongoDbWriteRequest> requestEntries,
//            Consumer<Collection<MongoDbWriteRequest>> requestResultConsumer) {
//
//        TableRequestsContainer container = new TableRequestsContainer(tablesConfig);
//        requestEntries.forEach(container::put);
//
//        CompletableFuture<BatchWriteItemResponse> future =
//                client.batchWriteItem(
//                        BatchWriteItemRequest.builder()
//                                .requestItems(container.getRequestItems())
//                                .build());
//
//        future.whenComplete(
//                (response, err) -> {
//                    if (err != null) {
//                        handleFullyFailedRequest(err, requestEntries, requestResultConsumer);
//                    } else if (response.unprocessedItems() != null
//                            && !response.unprocessedItems().isEmpty()) {
//                        handlePartiallyUnprocessedRequest(response, requestResultConsumer);
//                    } else {
//                        requestResultConsumer.accept(Collections.emptyList());
//                    }
//                });
//    }
//
//    private void handlePartiallyUnprocessedRequest(
//            BatchWriteItemResponse response,
//            Consumer<Collection<MongoDbWriteRequest>> requestResult) {
//        List<MongoDbWriteRequest> unprocessed = new ArrayList<>();
//
//        for (String tableName : response.unprocessedItems().keySet()) {
//            for (WriteModel<?> request : response.unprocessedItems().get(tableName)) {
//                unprocessed.add(new MongoDbWriteRequest(tableName, request));
//            }
//        }
//
//        LOG.warn("DynamoDB Sink failed to persist {} entries", unprocessed.size());
//        numRecordsOutErrorsCounter.inc(unprocessed.size());
//
//        requestResult.accept(unprocessed);
//    }
//
//    private void handleFullyFailedRequest(
//            Throwable err,
//            List<MongoDbWriteRequest> requestEntries,
//            Consumer<Collection<MongoDbWriteRequest>> requestResult) {
//        LOG.warn("DynamoDB Sink failed to persist {} entries", requestEntries.size(), err);
//        numRecordsOutErrorsCounter.inc(requestEntries.size());
//
//        if (DynamoDbExceptionUtils.isNotRetryableException(err.getCause())) {
//            getFatalExceptionCons()
//                    .accept(
//                            new DynamoDbSinkException(
//                                    "Encountered non-recoverable exception", err));
//        } else if (failOnError) {
//            getFatalExceptionCons()
//                    .accept(new DynamoDbSinkException.DynamoDbSinkFailFastException(err));
//        } else {
//            requestResult.accept(requestEntries);
//        }
//    }
//
//    @Override
//    protected void submitRequestEntries(List<MongoDbWriteRequest> requestEntries, Consumer<List<MongoDbWriteRequest>> requestResult) {
//
//    }
//
//    @Override
//    protected long getSizeInBytes(MongoDbWriteRequest requestEntry) {
//        // dynamodb calculates item size as a sum of all attributes and all values, but doing so on
//        // every operation may be too expensive, so this is just an estimate
//        return requestEntry.toString().getBytes(StandardCharsets.UTF_8).length;
//    }
//}