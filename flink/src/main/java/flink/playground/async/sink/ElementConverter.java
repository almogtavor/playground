package flink.playground.async.sink;

import flink.playground.async.sink.sink2.SinkWriter;
import org.apache.flink.annotation.PublicEvolving;

import java.io.Serializable;

/**
 * This interface specifies the mapping between elements of a stream to request entries that can be
 * sent to the destination. The mapping is provided by the end-user of a sink, not the sink creator.
 *
 * <p>The request entries contain all relevant information required to create and sent the actual
 * request. Eg, for Kinesis Data Streams, the request entry includes the payload and the partition
 * key.
 */
@PublicEvolving
public interface ElementConverter<InputT, RequestEntryT> extends Serializable {
    RequestEntryT apply(InputT element, SinkWriter.Context context);
}