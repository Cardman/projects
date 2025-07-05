package aiki.util;

import aiki.game.fight.*;
import code.util.*;

public abstract class TargetCoordssParam<V> extends AbsBasicMap<TargetCoords, V> {

    @Override
    protected boolean matchKeys(TargetCoords _one, TargetCoords _two) {
        return _one.eq(_two);
    }
}
