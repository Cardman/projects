package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class SelectItemBeanCancel implements NatCaller, IntBeanAction {
    private final SelectItemBean bean;
    public SelectItemBeanCancel() {
        this(null);
    }
    public SelectItemBeanCancel(SelectItemBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).cancel());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
