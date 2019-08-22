package code.formathtml.render;


public final class SegmentPart {

    private int begin;
    private int end;
    public SegmentPart(int _begin, int _end) {
        begin = _begin;
        end = _end;
    }

    public int getBegin() {
        return begin;
    }
    public int getEnd() {
        return end;
    }
}
