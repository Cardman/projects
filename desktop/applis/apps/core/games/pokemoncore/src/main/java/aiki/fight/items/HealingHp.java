package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class HealingHp extends HealingItem {

    private Rate hp;

    @Override
    public String getItemType() {
        return HEALING_HP;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateHealingItem(_data);
        DataInfoChecker.checkPositive(hp,_data);
    }

    public Rate getHp() {
        return hp;
    }

    public void setHp(Rate _hp) {
        hp = _hp;
    }
}
