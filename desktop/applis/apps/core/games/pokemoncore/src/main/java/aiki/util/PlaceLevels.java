package aiki.util;

import aiki.map.util.PlaceLevel;

public abstract class PlaceLevels<T> extends CommonMap<PlaceLevel,T> {
    @Override
    protected boolean eq(PlaceLevel _one, PlaceLevel _two) {
        return _one.eq(_two);
    }
}
