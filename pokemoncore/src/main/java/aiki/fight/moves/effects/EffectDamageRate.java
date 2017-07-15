package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class EffectDamageRate extends Effect {

    private Rate rateDamage;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (rateDamage.isZero()) {
            throw new DataException();
        }
    }
    public Rate getRateDamage() {
        return rateDamage;
    }

    public void setRateDamage(Rate _rateDamage) {
        rateDamage = _rateDamage;
    }

}
