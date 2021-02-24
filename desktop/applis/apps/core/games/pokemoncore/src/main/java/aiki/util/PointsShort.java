package aiki.util;

import code.util.CollCapacity;

public final class PointsShort extends Points<Short> {
    public PointsShort() {
    }
    public PointsShort(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Short def() {
        return (short)0;
    }
}
