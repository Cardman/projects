package aiki.util;

import aiki.map.Condition;

public final class CoordsListCoords {
    private final Coords key;
    private Condition value;

    public CoordsListCoords(Coords _key, Condition _value) {
        this.key = _key;
        this.value = _value;
    }

    public Coords getKey() {
        return key;
    }

    public Condition getValue() {
        return value;
    }

    public void setValue(Condition _value) {
        this.value = _value;
    }
}
