package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;


public final class EffectRestriction extends Effect {

    private boolean forbidTargetUsingItem;
    private MoveChoiceRestrictionType choiceRestriction;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (choiceRestriction == null) {
            _data.setError(true);
            return;

        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
    }

    public boolean getForbidTargetUsingItem() {
        return forbidTargetUsingItem;
    }

    public void setForbidTargetUsingItem(boolean _forbidTargetUsingItem) {
        forbidTargetUsingItem = _forbidTargetUsingItem;
    }

    public MoveChoiceRestrictionType getChoiceRestriction() {
        return choiceRestriction;
    }

    public void setChoiceRestriction(
            MoveChoiceRestrictionType _choiceRestriction) {
        choiceRestriction = _choiceRestriction;
    }

}
