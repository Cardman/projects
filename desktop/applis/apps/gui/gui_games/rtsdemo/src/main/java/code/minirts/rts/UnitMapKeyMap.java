package code.minirts.rts;

import code.util.AbsBasicMap;

public final class UnitMapKeyMap<V> extends AbsBasicMap<UnitMapKey,V> {
    @Override
    protected boolean matchKeys(UnitMapKey _one, UnitMapKey _two) {
        return _one.eq(_two);
    }
}
