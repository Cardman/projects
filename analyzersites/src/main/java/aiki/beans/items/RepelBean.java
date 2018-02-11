package aiki.beans.items;
import code.bean.Accessible;
import aiki.fight.items.Repel;

public class RepelBean extends ItemBean {

    @Accessible
    private long steps;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        Repel item_ = (Repel) getItem();
        steps = item_.getSteps();
    }
}
