package aiki.beans;

import code.bean.nat.*;
public final class WelcomeBeanClickAbilities implements NatCaller, IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickAbilities() {
        this(null);
    }

    public WelcomeBeanClickAbilities(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)(re(new PokemonBeanStruct(getBean()),new NaSt[0]))).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbilities());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
