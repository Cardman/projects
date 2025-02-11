package aiki.beans;

import code.bean.nat.*;

public final class WelcomeBeanClickPokedex implements NatCaller, IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickPokedex() {
        this(null);
    }

    public WelcomeBeanClickPokedex(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)(re(new PokemonBeanStruct(bean),new NaSt[0]))).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance()).clickPokedex());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
