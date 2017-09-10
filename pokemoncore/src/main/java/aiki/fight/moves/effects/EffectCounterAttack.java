package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.annot.RwXml;

@RwXml
public class EffectCounterAttack extends Effect {

    private StringMap<Rate> sufferingDamageTypes;

    private EnumMap<Statistic,Byte> droppedStatDirectMove;

    private Rate sufferingDamageDirectMove;

    private String protectFail;

    private String counterFail;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTypes().containsAllObj(sufferingDamageTypes.getKeys())) {
            throw new DataException();
        }
        for (Rate r: sufferingDamageTypes.values()) {
            if (r.isZero()) {
                throw new DataException();
            }
            if (!r.isZeroOrGt()) {
                throw new DataException();
            }
        }
        for (Statistic s: droppedStatDirectMove.getKeys()) {
            if (!s.isBoost()) {
                throw new DataException();
            }
            if (droppedStatDirectMove.getVal(s) >= 0) {
                throw new DataException();
            }
        }
        if (!sufferingDamageDirectMove.isZeroOrGt()) {
            throw new DataException();
        }
    }

    public StringMap<Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public void setSufferingDamageTypes(StringMap<Rate> _sufferingDamageTypes) {
        sufferingDamageTypes = _sufferingDamageTypes;
    }

    public EnumMap<Statistic,Byte> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }

    public void setDroppedStatDirectMove(EnumMap<Statistic,Byte> _droppedStatDirectMove) {
        droppedStatDirectMove = _droppedStatDirectMove;
    }

    public Rate getSufferingDamageDirectMove() {
        return sufferingDamageDirectMove;
    }

    public void setSufferingDamageDirectMove(Rate _sufferingDamageDirectMove) {
        sufferingDamageDirectMove = _sufferingDamageDirectMove;
    }

    public String getProtectFail() {
        return protectFail;
    }

    public void setProtectFail(String _protectFail) {
        protectFail = _protectFail;
    }

    public String getCounterFail() {
        return counterFail;
    }

    public void setCounterFail(String _counterFail) {
        counterFail = _counterFail;
    }
}
