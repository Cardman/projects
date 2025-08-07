package aiki.beans;

import aiki.game.fight.util.*;
import aiki.util.*;

public final class MoveTargetsChgString extends MoveTargetsParam<IntBeanChgValue<MoveTarget>> {

    @Override
    protected IntBeanChgValue<MoveTarget> def() {
        return new BeanChgMoveTarget();
    }
}
