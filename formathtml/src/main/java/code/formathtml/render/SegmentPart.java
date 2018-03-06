package code.formathtml.render;

import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class SegmentPart implements Equallable<SegmentPart>, Displayable {

    private int begin;
    private int end;
    public SegmentPart(int _begin, int _end) {
        begin = _begin;
        end = _end;
    }
    @Override
    public boolean eq(SegmentPart _g) {
        if (begin != _g.begin) {
            return false;
        }
        if (end != _g.end) {
            return false;
        }
        return true;
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
