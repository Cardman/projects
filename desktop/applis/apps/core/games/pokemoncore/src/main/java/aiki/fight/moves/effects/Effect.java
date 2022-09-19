package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.util.Ints;


public abstract class Effect {

    private TargetChoice targetChoice;

    private String fail;

    private Ints requiredSuccessfulEffects;

    /**
     * @param _data
     */
    public void validate(DataBase _data) {
        if (!requiredSuccessfulEffects.isEmpty() && requiredSuccessfulEffects.getMinimum(-1) < 0) {
            _data.setError(true);
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

    public Ints getRequiredSuccessfulEffects() {
        return requiredSuccessfulEffects;
    }

    public void setRequiredSuccessfulEffects(
            Ints _requiredSuccessfulEffects) {
        requiredSuccessfulEffects = _requiredSuccessfulEffects;
    }
}
