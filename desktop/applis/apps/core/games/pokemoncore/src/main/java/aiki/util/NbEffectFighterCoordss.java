package aiki.util;

import aiki.game.fight.util.NbEffectFighterCoords;
import code.util.*;
import code.util.core.BoolVal;

public final class NbEffectFighterCoordss extends AbsBasicMap<NbEffectFighterCoords, BoolVal> {

    @Override
    protected BoolVal def() {
        return BoolVal.FALSE;
    }

    @Override
    protected boolean matchKeys(NbEffectFighterCoords _one, NbEffectFighterCoords _two) {
        return _one.eq(_two);
    }
}
