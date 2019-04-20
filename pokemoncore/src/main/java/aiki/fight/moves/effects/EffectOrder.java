package aiki.fight.moves.effects;

import aiki.db.DataBase;


public final class EffectOrder extends Effect {

    private boolean targetAttacksLast;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!getTargetChoice().isWithChoice()) {
            _data.setError(true);

        }
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }

    public void setTargetAttacksLast(boolean _targetAttacksLast) {
        targetAttacksLast = _targetAttacksLast;
    }
}
