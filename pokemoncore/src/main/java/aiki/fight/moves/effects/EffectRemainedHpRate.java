package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectRemainedHpRate extends Effect {

    private Rate rateHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (rateHp.isZero()) {
            throw new DataException();
        }
    }

    public Rate getRateHp() {
        return rateHp;
    }

    public void setRateHp(Rate _rateHp) {
        rateHp = _rateHp;
    }

}
