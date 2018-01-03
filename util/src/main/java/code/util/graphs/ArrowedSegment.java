package code.util.graphs;
import code.util.ints.GraphElement;

public final class ArrowedSegment<T extends GraphElement<T>> {

    private final T from;

    private final T to;

    public ArrowedSegment(T _from, T _to) {
        from = _from;
        to = _to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
