package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class EffectOrder extends Effect {

    private boolean targetAttacksLast;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTargetWithChoice(getTargetChoice(),_data);
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }

    public void setTargetAttacksLast(boolean _targetAttacksLast) {
        targetAttacksLast = _targetAttacksLast;
    }
}
