package aiki.beans.items;

import aiki.fight.items.HealingItem;
import code.scripts.confs.PkScriptPages;

public class HealingItemBean extends ItemBean {
    static final String HEALING_ITEM_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_HEALINGITEM_HTML;
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