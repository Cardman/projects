package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectFullHpRate extends Effect {

    private Rate leftUserHp;
    private String restoredHp;
    private Rate closestFoeDamageRateHp;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!leftUserHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!closestFoeDamageRateHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (!leftUserHp.isZero()) {
            if (!restoredHp.isEmpty()) {
                throw new DataException();
            }
        }
    }
    public Rate getLeftUserHp() {
        return leftUserHp;
    }
    public void setLeftUserHp(Rate _leftUserHp) {
        leftUserHp = _leftUserHp;
    }
    public String getRestoredHp() {
        return restoredHp;
    }
    public void setRestoredHp(String _restoredHp) {
        restoredHp = _restoredHp;
    }
    public Rate getClosestFoeDamageRateHp() {
        return closestFoeDamageRateHp;
    }
    public void setClosestFoeDamageRateHp(Rate _closestFoeDamageRateHp) {
        closestFoeDamageRateHp = _closestFoeDamageRateHp;
    }
}
