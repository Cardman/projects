package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.util.Numbers;


public abstract class Effect {

    private TargetChoice targetChoice;

    private String fail;

    private Numbers<Integer> requiredSuccessfulEffects;

    /**
     * @param _data
     */
    public void validate(DataBase _data) {
        if (!requiredSuccessfulEffects.isEmpty()) {
            if (requiredSuccessfulEffects.getMinimum(-1) < 0) {
                _data.setError(true);
            }
        }
    }

    public TargetChoice getTargetChoice() {
        return targetChoice;
    }

    public void setTargetChoice(TargetChoice _targetChoice) {
        targetChoice = _targetChoice;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String _fail) {
        fail = _fail;
    }

    public Numbers<Integer> getRequiredSuccessfulEffects() {
        return requiredSuccessfulEffects;
    }

    public void setRequiredSuccessfulEffects(
            Numbers<Integer> _requiredSuccessfulEffects) {
        requiredSuccessfulEffects = _requiredSuccessfulEffects;
    }
}
