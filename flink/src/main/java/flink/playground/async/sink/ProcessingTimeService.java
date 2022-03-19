package flink.playground.async.sink;


import java.io.IOException;
import java.util.concurrent.ScheduledFuture;

/**
 * A service that allows to get the current processing time and register timers that will execute
 * the given {@link ProcessingTimeCallback} when firing.
 */
public interface ProcessingTimeService {
    /** Returns the current processing time. */
    long getCurrentProcessingTime();

    /**
     * Registers a task to be executed when (processing) time is {@code timestamp}.
     *
     * @param timestamp Time when the task is to be executed (in processing time)
     * @param target The task to be executed
     * @return The future that represents the scheduled task. This always returns some future, even
     *     if the timer was shut down
     */
    ScheduledFuture<?> registerTimer(long timestamp, ProcessingTimeCallback target);

    /**
     * A callback that can be registered via {@link #registerTimer(long, ProcessingTimeCallback)}.
     */
    interface ProcessingTimeCallback {
        /**
         * This method is invoked with the time which the callback register for.
         *
         * @param time The time this callback was registered for.
         */
        void onProcessingTime(long time) throws IOException, InterruptedException, Exception;
    }
}