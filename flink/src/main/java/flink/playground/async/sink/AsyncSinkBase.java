package flink.playground.async.sink;

import flink.playground.async.sink.sink2.StatefulSink;
import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.util.Preconditions;

import java.io.Serializable;

/**
 * A generic sink for destinations that provide an async client to persist data.
 *
 * <p>The design of the sink focuses on extensibility and a broad support of destinations. The core
 * of the sink is kept generic and free of any connector specific dependencies. The sink is designed
 * to participate in checkpointing to provide at-least once semantics, but it is limited to
 * destinations that provide a client that supports async requests.
 *
 * <p>Limitations:
 *
 * <ul>
 *   <li>The sink is designed for destinations that provide an async client. Destinations that
 *       cannot ingest events in an async fashion cannot be supported by the sink.
 *   <li>The sink usually persist InputTs in the order they are added to the sink, but reorderings
 *       may occur, eg, when RequestEntryTs need to be retried.
 *   <li>We are not considering support for exactly-once semantics at this point.
 * </ul>
 */
public abstract class AsyncSinkBase<InputT, RequestEntryT extends Serializable>
        implements StatefulSink<InputT, BufferedRequestState<RequestEntryT>> {

    private final ElementConverter<InputT, RequestEntryT> elementConverter;
    private final int maxBatchSize;
    private final int maxInFlightRequests;
    private final int maxBufferedRequests;
    private final long maxBatchSizeInBytes;
    private final long maxTimeInBufferMS;
    private final long maxRecordSizeInBytes;

    protected AsyncSinkBase(
            ElementConverter<InputT, RequestEntryT> elementConverter,
            int maxBatchSize,
            int maxInFlightRequests,
            int maxBufferedRequests,
            long maxBatchSizeInBytes,
            long maxTimeInBufferMS,
            long maxRecordSizeInBytes) {
        this.elementConverter =
                Preconditions.checkNotNull(
                        elementConverter,
                        "ElementConverter must be not null when initializing the AsyncSinkBase.");
        this.maxBatchSize = maxBatchSize;
        this.maxInFlightRequests = maxInFlightRequests;
        this.maxBufferedRequests = maxBufferedRequests;
        this.maxBatchSizeInBytes = maxBatchSizeInBytes;
        this.maxTimeInBufferMS = maxTimeInBufferMS;
        this.maxRecordSizeInBytes = maxRecordSizeInBytes;
    }

    protected ElementConverter<InputT, RequestEntryT> getElementConverter() {
        return elementConverter;
    }

    protected int getMaxBatchSize() {
        return maxBatchSize;
    }

    protected int getMaxInFlightRequests() {
        return maxInFlightRequests;
    }

    protected int getMaxBufferedRequests() {
        return maxBufferedRequests;
    }

    protected long getMaxBatchSizeInBytes() {
        return maxBatchSizeInBytes;
    }

    protected long getMaxTimeInBufferMS() {
        return maxTimeInBufferMS;
    }

    protected long getMaxRecordSizeInBytes() {
        return maxRecordSizeInBytes;
    }
}