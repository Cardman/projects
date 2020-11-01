package aiki.fight.items;

import aiki.db.DataBase;

public final class HealingSimpleItem extends HealingItem {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateHealingItem(_data);
    }

}
