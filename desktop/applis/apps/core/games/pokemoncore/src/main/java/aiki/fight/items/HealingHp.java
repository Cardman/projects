package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class HealingHp extends HealingItem {

    public static final String ITEM = "aiki.fight.items.HealingHp";

    private Rate hp;

    @Override
    public String getItemType() {
        return ITEM;
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
