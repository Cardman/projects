package aiki.util;

import aiki.game.fight.TargetCoords;
import code.maths.Rate;
import code.util.*;

public final class TargetCoordssRate extends AbsBasicMap<TargetCoords, Rate> {
    @Override
    protected Rate def() {
        return Rate.zero();
    }

    @Override
    protected boolean matchKeys(TargetCoords _one, TargetCoords _two) {
        return _one.eq(_two);
    }
}
