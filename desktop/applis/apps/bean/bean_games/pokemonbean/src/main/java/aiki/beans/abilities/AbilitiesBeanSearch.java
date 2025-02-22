package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public final class AbilitiesBeanSearch implements NatCaller, IntBeanAction {
    private final AbilitiesBean bean;
    public AbilitiesBeanSearch() {
        this(null);
    }
    public AbilitiesBeanSearch(AbilitiesBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
