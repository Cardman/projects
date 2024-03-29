package aiki.fight.status;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class StatusBeginRoundAutoDamage extends StatusBeginRound {

    private Rate power;

    private Statistic attack;

    private Statistic defense;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateStatusBeginRound(_data);
        DataInfoChecker.checkPositive(power,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),attack,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),attack,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBoost(),defense,_data);
        DataInfoChecker.checkStatisticListContains(Statistic.getStatisticsWithBase(),defense,_data);
    }

    public Rate getPower() {
        return power;
    }

    public void setPower(Rate _power) {
        power = _power;
    }

    public Statistic getAttack() {
        return attack;
    }

    public void setAttack(Statistic _attack) {
        attack = _attack;
    }

    public Statistic getDefense() {
        return defense;
    }

    public void setDefense(Statistic _defense) {
        defense = _defense;
    }
}
