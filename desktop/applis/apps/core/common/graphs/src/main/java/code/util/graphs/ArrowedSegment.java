package code.util.graphs;

public final class ArrowedSegment {

    private final SortedNumberedNode from;

    private final SortedNumberedNode to;

    public ArrowedSegment(SortedNumberedNode _from, SortedNumberedNode _to) {
        from = _from;
        to = _to;
    }

    public SortedNumberedNode getFrom() {
        return from;
    }

    public SortedNumberedNode getTo() {
        return to;
    }
}
