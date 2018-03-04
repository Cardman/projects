package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectClone extends Effect {

    private Rate hpRateClone;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (hpRateClone.isZero()) {
            throw new DataException();
        }
        if (!hpRateClone.isZeroOrGt()) {
            throw new DataException();
        }
    }
    public Rate getHpRateClone() {
        return hpRateClone;
    }

    public void setHpRateClone(Rate _hpRateClone) {
        hpRateClone = _hpRateClone;
    }
}
