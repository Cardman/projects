package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class SelectItemBeanSearch implements NatCaller, IntBeanAction {
    private final SelectItemBean bean;
    public SelectItemBeanSearch() {
        this(null);
    }
    public SelectItemBeanSearch(SelectItemBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
