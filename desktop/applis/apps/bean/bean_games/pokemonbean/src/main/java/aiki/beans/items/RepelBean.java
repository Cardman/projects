package aiki.beans.items;
import aiki.fight.items.Repel;

public class RepelBean extends ItemBean {
    private long steps;

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