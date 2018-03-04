package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectAlly extends Effect {

    private Rate multAllyDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.ALLIE) {
            if (getTargetChoice() != TargetChoice.ALLIES) {
                throw new DataException();
            }
        }
        if (!multAllyDamage.isZeroOrGt()) {
            throw new DataException();
        }
    }

    public Rate getMultAllyDamage() {
        return multAllyDamage;
    }


    public void setMultAllyDamage(Rate _multAllyDamage) {
        multAllyDamage = _multAllyDamage;
    }
}
