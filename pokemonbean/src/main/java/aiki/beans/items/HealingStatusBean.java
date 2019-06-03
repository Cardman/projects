package aiki.beans.items;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.items.HealingStatus;
import code.util.StringList;
import code.util.StringMap;

public class HealingStatusBean extends HealingItemBean {
    static final String HEALING_STATUS_BEAN="web/html/items/healingstatus.html";
    private StringList status;
    private boolean healingKo;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        HealingStatus item_ = (HealingStatus) getItem();
        healingKo = item_.getHealingKo();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringList status_;
        status_ = new StringList();
        for (String s: item_.getStatus()) {
            status_.add(s);
        }
        status_.sortElts(new ComparatorTrStrings(translatedStatus_));
        status = status_;
    }
    public String getTrStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = status.get(_index.intValue());
        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(Long _index) {
        String status_ = status.get(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }

    public boolean getHealingKo() {
        return healingKo;
    }

    public StringList getStatus() {
        return status;
    }
}