package aiki.beans.items;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingStatus;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public class HealingStatusBean extends HealingItemBean {
    static final String HEALING_STATUS_BEAN="web/html/items/healingstatus.html";
    private StringList status;
    private boolean healingKo;
    private Rate healedHpRate;
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
        if (item_ instanceof HealingHpStatus) {
            healedHpRate = ((HealingHpStatus)item_).getHealedHpRate();
        } else {
            healedHpRate = Rate.zero();
        }
    }

    public Rate getHealedHpRate() {
        return healedHpRate;
    }

    public String getTrStatus(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = status.get(_index);
        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        String status_ = status.get(_index);
        getForms().put(CST_STATUS, status_);
        return CST_STATUS;
    }

    public boolean getHealingKo() {
        return healingKo;
    }

    public StringList getStatus() {
        return status;
    }
}