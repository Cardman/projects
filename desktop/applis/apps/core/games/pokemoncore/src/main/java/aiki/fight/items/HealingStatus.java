package aiki.fight.items;

import aiki.db.DataBase;
import code.util.StringList;
import code.util.core.StringUtil;


public abstract class HealingStatus extends HealingItem {
    public static final String ITEM = "aiki.fight.items.HealingStatus";

    private StringList status;
    private boolean healingKo;

    @Override
    public String getItemType() {
        return ITEM;
    }

    protected final void validateHealingStatus(DataBase _data) {
        validateHealingItem(_data);
        if (!_data.getStatus().containsAllAsKeys(status)) {
            _data.setError(true);
        }
        if (healingKo) {
            return;
        }
        if (!status.isEmpty()) {
            return;
        }
        if (StringUtil.quickEq(getItemType(), ITEM)) {
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
