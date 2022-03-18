package flink.playground.transformation;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import java.util.Collections;

/**
 * An example of {@link AsyncFunction} using an async client to query an external service.
 */
public class SampleFunction extends RichAsyncFunction<Integer, String> {
    private static final long serialVersionUID = 1L;

    private transient AsyncClient client;

    @Override
    public void open(Configuration parameters) {
        client = new AsyncClient();
    }

    @Override
    public void asyncInvoke(final Integer input, final ResultFuture<String> resultFuture) {
        client.query(input)
                .whenComplete(
                        (response, error) -> {
                            if (response != null) {
                                resultFuture.complete(Collections.singletonList(response));
                            } else {
                                resultFuture.completeExceptionally(error);
                            }
                        });
    }
}