package flink.playground.transformation;

import org.apache.flink.configuration.Configuration;
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