//package flink.playground.connector;
//
//import flink.playground.async.sink.AsyncSinkBase;
//import flink.playground.async.sink.AsyncSinkWriter;
//import flink.playground.async.sink.ElementConverter;
//import flink.playground.model.ExampleData;
//import org.apache.flink.core.io.SimpleVersionedSerializer;
//import org.bson.Document;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//
//public class MongoSink extends AsyncSinkWriter<ExampleData, Document> {
//
//    @Override
//    protected void submitRequestEntries(List<PutRecordsRequestEntry> requestEntries, ResultFuture<PutRecordsRequestEntry> requestResult) {
//
//        // create a batch request
//        PutRecordsRequest batchRequest = PutRecordsRequest
//                .builder()
//                .records(requestEntries)
//                .streamName(streamName)
//                .build();
//
//        // call api with batch request
//        CompletableFuture<PutRecordsResponse> future = client.putRecords(batchRequest);
//
//        // re-queue elements of failed requests
//        future.whenComplete((response, err) -> {
//            if (response.failedRecordCount() > 0) {
//                ArrayList<PutRecordsRequestEntry> failedRequestEntries = new ArrayList<>(response.failedRecordCount());
//                List<PutRecordsResultEntry> records = response.records();
//
//                for (int i = 0; i < records.size(); i++) {
//                    if (records.get(i).errorCode() != null) {
//                        failedRequestEntries.add(requestEntries.get(i));
//                    }
//                }
//
//                requestResult.complete(failedRequestEntries);
//            } else {
//                requestResult.complete(Collections.emptyList());
//            }
//
//            //TODO: handle errors of the entire request...
//        });
//    }
//}