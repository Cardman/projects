package aiki.beans.items;

import aiki.facade.FacadeGame;

public final class SellingItemBean extends ItemBean {

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
    }

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
    }
}