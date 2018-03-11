package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;
import code.util.annot.RwXml;


@RwXml
public final class EffectRestriction extends Effect {

    private boolean forbidTargetUsingItem;
    private MoveChoiceRestrictionType choiceRestriction;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (choiceRestriction == null) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
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
    public void setChoiceRestriction(MoveChoiceRestrictionType _choiceRestriction) {
        choiceRestriction = _choiceRestriction;
    }

}
