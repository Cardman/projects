package aiki.beans.items;

import aiki.beans.StringMapObject;
import aiki.facade.FacadeGame;

public final class SellingItemBean extends ItemBean {

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
    }

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
    }
}