package aiki.util;

import aiki.game.fight.TargetCoords;
import code.maths.Rate;

public final class TargetCoordssRate extends CommonMap<TargetCoords, Rate> {
    @Override
    protected Rate def() {
        return Rate.zero();
    }

    @Override
    protected boolean eq(TargetCoords _one, TargetCoords _two) {
        return _one.eq(_two);
    }
}
