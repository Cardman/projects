package aiki.beans.items;

import aiki.beans.CommonBean;
import aiki.fight.items.HealingItem;
import code.scripts.pages.aiki.*;

public abstract class HealingItemBean extends ItemBean {
    static final String HEALING_ITEM_BEAN= CommonBean.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML;
    private boolean healingTeam;

    protected void beforeDisplayingHealItem() {
        beforeDisplayingItem();
        HealingItem item_ = (HealingItem) getItem();
        healingTeam = item_.getHealingTeam();
        initHappiness(item_.getHappiness());
    }
    protected void healItem() {
        displayBoolTrue(toInt(healingTeam),MessagesPkBean.IT_HEALINGITEM,MessagesDataItemsHealingitem.M_P_24_HEAL_TEAM);
        buildHappiness(MessagesPkBean.IT_HEALINGITEM,MessagesDataItemsHealingitem.M_P_24_HAPPINESS,MessagesDataItemsHealingitem.M_P_24_HAPPINESS_OTHER_BALL,MessagesDataItemsHealingitem.M_P_24_HAPPINESS_BALL,MessagesDataItemsHealingitem.M_P_24_HAPPINESS_BOOST);
    }

    public boolean getHealingTeam() {
        return healingTeam;
    }

}