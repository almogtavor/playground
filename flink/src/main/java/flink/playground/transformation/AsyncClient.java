package flink.playground.transformation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/** A simple asynchronous client that simulates interacting with an unreliable external service. */
public class AsyncClient {

    public CompletableFuture<String> query(int key) {
        return CompletableFuture.supplyAsync(
                () -> {
                    long sleep = (long) (ThreadLocalRandom.current().nextFloat() * 100);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("AsyncClient was interrupted", e);
                    }

                    if (ThreadLocalRandom.current().nextFloat() < 0.001f) {
                        throw new RuntimeException("wahahahaha...");
                    } else {
                        return "key" + (key % 10);
                    }
                });
    }
}