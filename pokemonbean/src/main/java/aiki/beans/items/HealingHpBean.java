package aiki.beans.items;
import aiki.fight.items.HealingHp;
import code.maths.Rate;

public class HealingHpBean extends HealingItemBean {
    private Rate hp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingHp item_ = (HealingHp) getItem();
        hp = item_.getHp();
    }

    public Rate getHp() {
        return hp;
    }
}