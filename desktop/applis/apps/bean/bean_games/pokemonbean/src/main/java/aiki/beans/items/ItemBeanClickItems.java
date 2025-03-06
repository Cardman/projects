package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public final class ItemBeanClickItems implements NatCaller, IntBeanAction {
    private final ItemBean bean;

    public ItemBeanClickItems() {
        this(null);
    }

    public ItemBeanClickItems(ItemBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemBean) ((PokemonBeanStruct)_instance).getInstance()).clickItems());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
