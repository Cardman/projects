package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public final class PokemonBeanClickPokedex implements NatCaller, IntBeanAction {
    private final PokemonBean bean;

    public PokemonBeanClickPokedex() {
        this(null);
    }

    public PokemonBeanClickPokedex(PokemonBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickPokedex());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
