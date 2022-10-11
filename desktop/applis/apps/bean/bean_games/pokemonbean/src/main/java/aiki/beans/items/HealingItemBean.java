package aiki.beans.items;

import aiki.fight.items.HealingItem;

public class HealingItemBean extends ItemBean {
    static final String HEALING_ITEM_BEAN="web/html/items/healingitem.html";
    private boolean healingTeam;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        HealingItem item_ = (HealingItem) getItem();
        healingTeam = item_.getHealingTeam();
        initHappiness(item_.getHappiness());
    }

    public boolean getHealingTeam() {
        return healingTeam;
    }

}