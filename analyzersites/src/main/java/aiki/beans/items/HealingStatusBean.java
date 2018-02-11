package aiki.beans.items;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.items.HealingStatus;

public class HealingStatusBean extends HealingItemBean {

    @Accessible
    private final String healingStatusBean="web/html/items/healingstatus.html";

    @Accessible
    private StringList status;

    @Accessible
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

    @Accessible
    private String getTrStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = status.get(_index.intValue());
        return translatedStatus_.getVal(status_);
    }

    @Accessible
    private String clickStatus(Long _index) {
        String status_ = status.get(_index.intValue());
        getForms().put(STATUS, status_);
        return STATUS;
    }
}
