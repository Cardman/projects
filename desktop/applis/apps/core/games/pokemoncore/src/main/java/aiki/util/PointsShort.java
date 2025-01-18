package aiki.util;

import code.util.CollCapacity;

public final class PointsShort extends Points<Integer> {
    public PointsShort() {
    }
    public PointsShort(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Integer def() {
        return 0;
    }
}
