package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringMap;


public final class EffectCounterAttack extends Effect {

    private StringMap<Rate> sufferingDamageTypes;

    private EnumMap<Statistic, Byte> droppedStatDirectMove;

    private Rate sufferingDamageDirectMove;

    private String protectFail;

    private String counterFail;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getTypes().containsAllObj(sufferingDamageTypes.getKeys())) {
            _data.setError(true);
            return;

        }
        for (Rate r : sufferingDamageTypes.values()) {
            if (r.isZero()) {
                _data.setError(true);
                return;

            }
            if (!r.isZeroOrGt()) {
                _data.setError(true);
                return;

            }
        }
        for (Statistic s : droppedStatDirectMove.getKeys()) {
            if (!s.isBoost()) {
                _data.setError(true);
                return;

            }
            if (droppedStatDirectMove.getVal(s) >= 0) {
                _data.setError(true);
                return;

            }
        }
        if (!sufferingDamageDirectMove.isZeroOrGt()) {
            _data.setError(true);

        }
    }

    public StringMap<Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public void setSufferingDamageTypes(StringMap<Rate> _sufferingDamageTypes) {
        sufferingDamageTypes = _sufferingDamageTypes;
    }

    public EnumMap<Statistic, Byte> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }

    public void setDroppedStatDirectMove(
            EnumMap<Statistic, Byte> _droppedStatDirectMove) {
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
