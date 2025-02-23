package aiki.beans.items;
import aiki.beans.*;
import aiki.facade.*;
import aiki.fight.items.*;
import code.maths.*;
import code.scripts.pages.aiki.*;

public final class HealingHpBean extends HealingItemBean {
    private Rate hp;

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        healItem();
        displayIntDef(hp, MessagesPkBean.IT_HEALINGHP, MessagesDataItemsHealinghp.M_P_22_HEAL_HP);
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingHealItem();
        HealingHp item_ = (HealingHp) getItem();
        hp = item_.getHp();
    }

    public Rate getHp() {
        return hp;
    }
}