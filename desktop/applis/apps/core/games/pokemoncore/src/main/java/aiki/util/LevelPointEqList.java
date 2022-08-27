package aiki.util;

import code.util.AbEqList;

public final class LevelPointEqList extends AbEqList<LevelPoint> {

    @Override
    public boolean match(LevelPoint _one, LevelPoint _two) {
        return _one.eq(_two);
    }
}
