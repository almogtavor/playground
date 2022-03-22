package flink.playground.connector;

import flink.playground.async.sink.AsyncSinkBase;
import flink.playground.async.sink.AsyncSinkBaseBuilder;
import flink.playground.model.ExampleData;
import org.bson.Document;

public class MongoDbSinkBuilder<InputT> extends AsyncSinkBaseBuilder<InputT, Document, MongoDbSinkBuilder<InputT>> {


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


    @Override
    public AsyncSinkBase<InputT, Document> build() {
        return null;
    }
}