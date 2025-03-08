package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.*;
import aiki.fight.items.*;
import code.maths.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class HealingStatusBean extends HealingItemBean {
    static final String HEALING_STATUS_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGSTATUS_HTML;
    private CustList<TranslatedKey> status;
    private boolean healingKo;
    private Rate healedHpRate;
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
        healItem();
        displayBoolTrue(toInt(healingKo),MessagesPkBean.IT_HEALINGSTATUS,MessagesDataItemsHealingstatus.M_P_26_HEAL_KO);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,status,MessagesPkBean.IT_HEALINGSTATUS,MessagesDataItemsHealingstatus.M_P_26_STATUS);
        displayIntDef(healedHpRate,MessagesPkBean.IT_HEALINGHPSTATUS,MessagesDataItemsHealinghpstatus.M_P_23_RATE);
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingHealItem();
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