package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class AddPokemonBeanAdd implements NatCaller, IntBeanAction {
    private final AddPokemonBean bean;
    public AddPokemonBeanAdd() {
        this(null);
    }
    public AddPokemonBeanAdd(AddPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).add());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
