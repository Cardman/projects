package aiki.util;

import code.util.CollCapacity;

public abstract class Points<T> extends CommonMap<Point,T> {
    protected Points() {
    }
    protected Points(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean eq(Point _one, Point _two) {
        return _one.eq(_two);
    }
}
