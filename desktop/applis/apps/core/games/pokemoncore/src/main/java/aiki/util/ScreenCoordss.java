package aiki.util;

import aiki.map.util.ScreenCoords;
import code.util.*;

public abstract class ScreenCoordss<T> extends AbsBasicMap<ScreenCoords,T> {
    protected ScreenCoordss() {
    }
    protected ScreenCoordss(AbsBasicMap<ScreenCoords,T> _other) {
        super(new CollCapacity(_other.size()));
        addAllEntries(_other);
    }
    @Override
    protected boolean matchKeys(ScreenCoords _one, ScreenCoords _two) {
        return _one.eq(_two);
    }
}
