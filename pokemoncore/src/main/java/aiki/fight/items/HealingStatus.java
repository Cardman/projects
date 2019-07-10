package aiki.fight.items;

import aiki.db.DataBase;
import code.util.StringList;


public abstract class HealingStatus extends HealingItem {
    public static final String ITEM = "aiki.fight.items.HealingStatus";

    private StringList status;
    private boolean healingKo;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getStatus().containsAllAsKeys(status)) {
            _data.setError(true);
            return;

        }
        if (healingKo) {
            return;
        }
        if (!status.isEmpty()) {
            return;
        }
        if (StringList.quickEq(getItemType(), ITEM)) {
            _data.setError(true);

        }
    }

    public StringList getStatus() {
        return status;
    }

    public void setStatus(StringList _status) {
        status = _status;
    }

    public boolean getHealingKo() {
        return healingKo;
    }

    public void setHealingKo(boolean _healingKo) {
        healingKo = _healingKo;
    }
}
