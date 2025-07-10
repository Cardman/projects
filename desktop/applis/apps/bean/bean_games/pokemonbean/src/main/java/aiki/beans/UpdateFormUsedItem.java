package aiki.beans;

import aiki.beans.simulation.*;

public final class UpdateFormUsedItem extends IntBeanActionPartForm{

    public UpdateFormUsedItem(SimulationBean _s) {
        super(_s);
    }

    @Override
    public PageFormSimu actionBean(PageFormSimu _form) {
        getSimu().usedItemsContent(getSimu().sortedUsedItems(), _form);
        return _form;
    }
}
