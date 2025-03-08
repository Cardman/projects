package aiki.beans.items;
import aiki.facade.*;
import aiki.fight.items.*;
import code.scripts.pages.aiki.*;

public final class RepelBean extends ItemBean {
    private long steps;
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
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