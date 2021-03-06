package aiki.fight.status;

import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.maths.Rate;


public final class StatusBeginRoundAutoDamage extends StatusBeginRound {

    private Rate power;

    private Statistic attack;

    private Statistic defense;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateStatusBeginRound(_data);
        if (!power.isZeroOrGt()) {
            _data.setError(true);
        }
        if (power.isZero()) {
            _data.setError(true);
        }
        if (!attack.isBoost()) {
            _data.setError(true);
        }
        if (!attack.isWithBaseStatistic()) {
            _data.setError(true);
        }
        if (!defense.isBoost()) {
            _data.setError(true);
        }
        if (!defense.isWithBaseStatistic()) {
            _data.setError(true);

        }
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
