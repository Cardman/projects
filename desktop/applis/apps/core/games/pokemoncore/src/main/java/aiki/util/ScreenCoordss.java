package aiki.util;

import aiki.map.util.ScreenCoords;

public abstract class ScreenCoordss<T> extends CommonMap<ScreenCoords,T> {
    protected ScreenCoordss() {
    }
    protected ScreenCoordss(CommonMap<ScreenCoords,T> _other) {
        super(_other);
    }
    @Override
    protected boolean eq(ScreenCoords _one, ScreenCoords _two) {
        return _one.eq(_two);
    }
}
