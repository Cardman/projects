package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanClickIndex implements NatCaller, IntBeanAction {
    private final AbilityBean bean;

    public AbilityBeanClickIndex() {
        this(null);
    }

    public AbilityBeanClickIndex(AbilityBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).clickIndex());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
