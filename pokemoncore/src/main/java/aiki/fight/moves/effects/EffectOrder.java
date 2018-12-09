package aiki.fight.moves.effects;

import aiki.DataBase;


public final class EffectOrder extends Effect {

    private boolean targetAttacksLast;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        // if (getTargetChoice() == TargetChoice.LANCEUR) {
        // _data.setError(true);

        // }
        if (!getTargetChoice().isWithChoice()) {
            _data.setError(true);
            return;

        }
    }

    public boolean getTargetAttacksLast() {
        return targetAttacksLast;
    }

    public void setTargetAttacksLast(boolean _targetAttacksLast) {
        targetAttacksLast = _targetAttacksLast;
    }
}
