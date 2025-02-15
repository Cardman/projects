package aiki.beans.items;

import aiki.beans.*;
import aiki.fight.items.*;
import code.maths.*;
import code.scripts.confs.*;
import code.util.*;

public class HealingStatusBean extends HealingItemBean {
    static final String HEALING_STATUS_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML;
    private CustList<TranslatedKey> status;
    private boolean healingKo;
    private Rate healedHpRate;
    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingStatus item_ = (HealingStatus) getItem();
        healingKo = item_.getHealingKo();
        status = listTrStringsSt(item_.getStatus(),getFacade());
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
        return status.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        String status_ = status.get(_index);
//        return translatedStatus_.getVal(status_);
    }
    public String clickStatus(int _index) {
        return tryRedirect(status.get(_index));
    }

    public boolean getHealingKo() {
        return healingKo;
    }

    public CustList<TranslatedKey> getStatus() {
        return status;
    }
}