package aiki.beans.items;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingStatus;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;

public class HealingStatusBean extends HealingItemBean {
    static final String HEALING_STATUS_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML;
    private StringList status;
    private boolean healingKo;
    private Rate healedHpRate;
    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        HealingStatus item_ = (HealingStatus) getItem();
        healingKo = item_.getHealingKo();
        StringList status_;
        status_ = new StringList();
        for (String s: item_.getStatus()) {
            status_.add(s);
        }
        status_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        String status_ = status.get(_index);
        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        String status_ = status.get(_index);
        return tryRedirectSt(status_);
    }

    public boolean getHealingKo() {
        return healingKo;
    }

    public StringList getStatus() {
        return status;
    }
}