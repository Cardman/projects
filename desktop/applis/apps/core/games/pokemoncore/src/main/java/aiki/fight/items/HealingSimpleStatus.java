package aiki.fight.items;

import aiki.db.DataBase;

public final class HealingSimpleStatus extends HealingStatus {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateHealingStatus(_data);
    }

}
