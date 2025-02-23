package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.*;

public final class SimpleHealingItemBean extends HealingItemBean {

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        healItem();
    }

    @Override
    public void beforeDisplaying() {
        beforeDisplayingHealItem();
    }

}