package aiki.beans;

import aiki.db.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;

public class BeanChgMoveTarget implements IntBeanChgMoveTarget {

    private String move = DataBase.EMPTY_STRING;

    private TargetCoords target = TargetCoords.def();

    @Override
    public MoveTarget genericValue() {
        return valueMt();
    }

    @Override
    public MoveTarget valueMt() {
        return new MoveTarget(move,target);
    }

    @Override
    public void valueMt(MoveTarget _v) {
        move = _v.getMove();
        target = _v.getTarget();
    }
}
