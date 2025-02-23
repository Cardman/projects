package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public final class ItemsBeanSearch implements NatCaller, IntBeanAction {
    private final ItemsBean bean;
    public ItemsBeanSearch() {
        this(null);
    }
    public ItemsBeanSearch(ItemsBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (ItemsBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
