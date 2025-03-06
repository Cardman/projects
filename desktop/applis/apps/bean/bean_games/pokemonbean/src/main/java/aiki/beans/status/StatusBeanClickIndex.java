package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
public final class StatusBeanClickIndex implements NatCaller, IntBeanAction {
    private final StatusBean bean;

    public StatusBeanClickIndex() {
        this(null);
    }

    public StatusBeanClickIndex(StatusBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).clickIndex());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
