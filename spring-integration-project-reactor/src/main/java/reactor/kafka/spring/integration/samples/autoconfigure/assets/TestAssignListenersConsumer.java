package reactor.kafka.spring.integration.samples.autoconfigure.assets;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class TestAssignListenersConsumer implements Consumer {

    @Override
    public void accept(Object o) {
        System.out.println("bla");
    }

    @NotNull
    @Override
    public Consumer andThen(@NotNull Consumer after) {
        return Consumer.super.andThen(after);
    }
}
