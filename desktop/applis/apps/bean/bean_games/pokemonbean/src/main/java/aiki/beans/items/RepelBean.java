package aiki.beans.items;
import aiki.beans.*;
import aiki.facade.*;
import aiki.fight.items.*;
import code.scripts.pages.aiki.*;

public final class RepelBean extends ItemBean {
    private long steps;
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        displayIntDef(steps,MessagesPkBean.IT_REPEL,MessagesDataItemsRepel.M_P_30_STEPS);
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        Repel item_ = (Repel) getItem();
        steps = item_.getSteps();
    }

    public long getSteps() {
        return steps;
    }
}