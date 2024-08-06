package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class HealingHpStatus extends HealingStatus {

    private Rate healedHpRate;

    @Override
    public String getItemType() {
        return HEALING_HP_STATUS;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateHealingStatus(_data);
        DataInfoChecker.checkPositive(healedHpRate,_data);
    }

    public Rate getHealedHpRate() {
        return healedHpRate;
    }

    public void setHealedHpRate(Rate _healedHpRate) {
        healedHpRate = _healedHpRate;
    }
}
