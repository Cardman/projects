package code.formathtml.render;

import code.util.ints.Displayable;

public final class SegmentPart implements Displayable {

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
    @Override
    public String display() {
        return new StringBuilder().append(begin).append(" to ").append(end).toString();
    }
}
