package aiki.fight.items;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.serialize.CheckedData;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public class HealingStatus extends HealingItem {
    public static final String ITEM = "aiki.fight.items.HealingStatus";

    private StringList status;
    @CheckedData
    private boolean healingKo;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getStatus().containsAllAsKeys(status)) {
            throw new DataException();
        }
        if (StringList.quickEq(getItemType(), ITEM)) {
            if (healingKo) {
                return;
            }
            if (!status.isEmpty() ) {
                return;
            }
            throw new DataException();
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
