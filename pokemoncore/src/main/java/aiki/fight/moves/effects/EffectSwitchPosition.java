package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.serialize.CheckedData;

@CheckedData
public final class EffectSwitchPosition extends Effect {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.ALLIE) {
            throw new DataException();
        }
    }
}
