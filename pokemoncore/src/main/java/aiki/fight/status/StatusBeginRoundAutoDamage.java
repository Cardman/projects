package aiki.fight.status;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.enums.Statistic;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class StatusBeginRoundAutoDamage extends StatusBeginRound {

    private Rate power;

    private Statistic attack;

    private Statistic defense;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!power.isZeroOrGt()) {
            throw new DataException();
        }
        if (power.isZero()) {
            throw new DataException();
        }
        if (!attack.isBoost()) {
            throw new DataException();
        }
        if (!attack.isWithBaseStatistic()) {
            throw new DataException();
        }
        if (!defense.isBoost()) {
            throw new DataException();
        }
        if (!defense.isWithBaseStatistic()) {
            throw new DataException();
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
