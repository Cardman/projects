package aiki.util;

import aiki.map.util.PlaceLevel;
import code.util.*;

public abstract class PlaceLevels<T> extends AbsBasicMap<PlaceLevel,T> {
    @Override
    protected boolean matchKeys(PlaceLevel _one, PlaceLevel _two) {
        return _one.eq(_two);
    }
}
