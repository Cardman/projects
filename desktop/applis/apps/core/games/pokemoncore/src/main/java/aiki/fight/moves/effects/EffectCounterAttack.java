package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.IdMap;
import code.util.StringMap;


public final class EffectCounterAttack extends Effect {

    private StringMap<Rate> sufferingDamageTypes;

    private IdMap<Statistic, Integer> droppedStatDirectMove;

    private Rate sufferingDamageDirectMove;

    private String protectFail;

    private String counterFail;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),sufferingDamageTypes.getKeys(),_data);
        DataInfoChecker.checkPositiveRates(sufferingDamageTypes.values(),_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),droppedStatDirectMove.getKeys(),_data);
        DataInfoChecker.checkNegativeBytes(droppedStatDirectMove.values(),_data);
        DataInfoChecker.checkPositiveOrZero(sufferingDamageDirectMove,_data);
    }

    public StringMap<Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public void setSufferingDamageTypes(StringMap<Rate> _sufferingDamageTypes) {
        sufferingDamageTypes = _sufferingDamageTypes;
    }

    public IdMap<Statistic, Integer> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }

    public void setDroppedStatDirectMove(
            IdMap<Statistic, Integer> _droppedStatDirectMove) {
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
