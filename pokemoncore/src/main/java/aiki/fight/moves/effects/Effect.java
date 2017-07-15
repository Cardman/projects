package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.datacheck.CheckedData;
import code.util.Numbers;
import code.util.annot.RwXml;

@RwXml
public abstract class Effect {

    @CheckedData
    private TargetChoice targetChoice;

    @CheckedData
    private String fail;

    private Numbers<Integer> requiredSuccessfulEffects;

    /**
    @param _data
    */
    public void validate(DataBase _data) {
        if (!requiredSuccessfulEffects.isEmpty()) {
            if (requiredSuccessfulEffects.getMinimum() < 0) {
                throw new DataException();
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

    public void setRequiredSuccessfulEffects(Numbers<Integer> _requiredSuccessfulEffects) {
        requiredSuccessfulEffects = _requiredSuccessfulEffects;
    }
}
