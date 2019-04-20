package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;


public final class EffectRestriction extends Effect {

    private boolean forbidTargetUsingItem;
    private MoveChoiceRestrictionType choiceRestriction;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);

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
