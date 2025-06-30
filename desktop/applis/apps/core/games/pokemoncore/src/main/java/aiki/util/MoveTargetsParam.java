package aiki.util;

import aiki.game.fight.util.*;
import code.util.*;

public abstract class MoveTargetsParam<V> extends AbsBasicMap<MoveTarget,V> {
    protected MoveTargetsParam() {
    }
    protected MoveTargetsParam(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected boolean matchKeys(MoveTarget _one, MoveTarget _two) {
        return _one.eq(_two);
    }

}
