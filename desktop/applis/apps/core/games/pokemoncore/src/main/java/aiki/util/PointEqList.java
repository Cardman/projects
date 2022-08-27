package aiki.util;

import code.util.AbEqList;
import code.util.CollCapacity;
import code.util.ints.Listable;

public final class PointEqList extends AbEqList<Point> {
    public PointEqList() {
    }

    public PointEqList(Listable<Point> _c) {
        super(_c);
    }

    public PointEqList(CollCapacity _capacity) {
        super(_capacity);
    }
    @Override
    public boolean match(Point _one, Point _two) {
        return _one.eq(_two);
    }
}
