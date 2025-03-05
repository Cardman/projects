package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectAbilityBeanClickAbility implements NatCaller, IntBeanAction {
    private final SelectAbilityBean bean;
    private final int index;
    public SelectAbilityBeanClickAbility() {
        this(null,0);
    }
    public SelectAbilityBeanClickAbility(SelectAbilityBean _b,int _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(index)})).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectAbilityBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
