package aiki.beans;

import aiki.db.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;

public class BeanChgAffectedMove implements IntBeanChgAffectedMove {
    private String move = DataBase.EMPTY_STRING;
    private ActivityOfMove activity = new ActivityOfMove();

    @Override
    public AffectedMove genericValue() {
        return valAff();
    }

    @Override
    public AffectedMove valAff() {
        return new AffectedMove(move,activity);
    }

    @Override
    public void valAff(AffectedMove _v) {
        move = _v.getMove();
        activity = _v.getActivity();
    }

}
