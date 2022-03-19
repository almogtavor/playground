package flink.playground.async.sink;

public class RequestEntryWrapper<RequestEntryT> {

    private final RequestEntryT requestEntry;
    private final long size;

    public RequestEntryWrapper(RequestEntryT requestEntry, long size) {
        this.requestEntry = requestEntry;
        this.size = size;
    }

    public RequestEntryT getRequestEntry() {
        return requestEntry;
    }

    public long getSize() {
        return size;
    }
}