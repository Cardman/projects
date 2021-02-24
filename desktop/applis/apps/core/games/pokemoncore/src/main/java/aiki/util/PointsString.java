package aiki.util;

import code.util.CollCapacity;

public final class PointsString extends Points<String> {
    public PointsString() {
    }
    public PointsString(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected String def() {
        return "";
    }
}
