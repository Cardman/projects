package aiki.beans.items;
import aiki.fight.items.HealingHpStatus;
import code.maths.Rate;

public class HealingHpStatusBean extends HealingStatusBean {
    private Rate healedHpRate;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingHpStatus item_ = (HealingHpStatus) getItem();
        healedHpRate = item_.getHealedHpRate();
    }

    public Rate getHealedHpRate() {
        return healedHpRate;
    }
}