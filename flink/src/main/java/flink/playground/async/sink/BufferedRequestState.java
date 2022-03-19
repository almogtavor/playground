package flink.playground.async.sink;


import org.apache.flink.annotation.PublicEvolving;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Class holding state of {@link AsyncSinkWriter} needed at taking a snapshot. The state captures
 * the {@code bufferedRequestEntries} buffer for the writer at snapshot to resume the requests. This
 * guarantees at least once semantic in sending requests where restoring from a snapshot where
 * buffered requests were flushed to the sink will cause duplicate requests.
 *
 * @param <RequestEntryT> request type.
 */
@PublicEvolving
public class BufferedRequestState<RequestEntryT extends Serializable> implements Serializable {
    private final List<RequestEntryWrapper<RequestEntryT>> bufferedRequestEntries;
    private final long stateSize;

    public BufferedRequestState(Deque<RequestEntryWrapper<RequestEntryT>> bufferedRequestEntries) {
        this.bufferedRequestEntries = new ArrayList<>(bufferedRequestEntries);
        this.stateSize = calculateStateSize();
    }

    public BufferedRequestState(List<RequestEntryWrapper<RequestEntryT>> bufferedRequestEntries) {
        this.bufferedRequestEntries = new ArrayList<>(bufferedRequestEntries);
        this.stateSize = calculateStateSize();
    }

    public List<RequestEntryWrapper<RequestEntryT>> getBufferedRequestEntries() {
        return bufferedRequestEntries;
    }

    public long getStateSize() {
        return stateSize;
    }

    private long calculateStateSize() {
        long stateSize = 0;
        for (RequestEntryWrapper<RequestEntryT> requestEntryWrapper : bufferedRequestEntries) {
            stateSize += requestEntryWrapper.getSize();
        }

        return stateSize;
    }

    public static <T extends Serializable> BufferedRequestState<T> emptyState() {
        return new BufferedRequestState<>(Collections.emptyList());
    }
}