package aiki.beans.items;

import aiki.facade.*;

public final class SimpleHealingItemBean extends HealingItemBean {

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
        healItem();
    }

    @Override
    public void beforeDisplaying() {
        beforeDisplayingHealItem();
    }

}