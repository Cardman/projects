package aiki.beans.items;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.items.HealingHp;

public class HealingHpBean extends HealingItemBean {

    @Accessible
    private Rate hp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        HealingHp item_ = (HealingHp) getItem();
        hp = item_.getHp();
    }
}
