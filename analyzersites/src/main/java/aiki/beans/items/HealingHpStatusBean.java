package aiki.beans.items;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.items.HealingHpStatus;

public class HealingHpStatusBean extends HealingStatusBean {

    @Accessible
    private Rate healedHpRate;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingHpStatus item_ = (HealingHpStatus) getItem();
        healedHpRate = item_.getHealedHpRate();
    }
}
