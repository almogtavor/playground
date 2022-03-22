package flink.playground.async.sink;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.metrics.Counter;
import org.apache.flink.metrics.Gauge;
import org.apache.flink.metrics.groups.OperatorMetricGroup;

/**
 * Pre-defined metrics for sinks.
 *
 * <p>You should only update the metrics in the main operator thread.
 */
public interface SinkWriterMetricGroup extends OperatorMetricGroup {

    /**
     * @deprecated use {@link #getNumRecordsSendErrorsCounter()} instead.
     */
    @Deprecated
    Counter getNumRecordsOutErrorsCounter();

    /**
     * The total number of records failed to send.
     */
    Counter getNumRecordsSendErrorsCounter();

    /**
     * The total number of records have been sent to the downstream system.
     *
     * <p>Note: this counter will count all records the SinkWriter sent. From SinkWirter's
     * perspective, these records have been sent to the downstream system, but the downstream system
     * may have issue to perform the persistence action within its scope. Therefore, this count may
     * include the number of records that are failed to write by the downstream system, which should
     * be counted by {@link #getNumRecordsSendErrorsCounter()}.
     */
    Counter getNumRecordsSendCounter();

    /**
     * The total number of output send bytes since the task started.
     */
    Counter getNumBytesSendCounter();

    /**
     * Sets an optional gauge for the time it takes to send the last record.
     *
     * <p>This metric is an instantaneous value recorded for the last processed record.
     *
     * <p>If this metric is eagerly calculated, this metric should NOT be updated for each record.
     * Instead, update this metric for each batch of record or sample every X records.
     *
     * <p>Note for asynchronous sinks, the time must be accessible from the main operator thread.
     * For example, a `volatile` field could be set in the async thread and lazily read in the
     * gauge.
     */
    void setCurrentSendTimeGauge(Gauge<Long> currentSendTimeGauge);
}
