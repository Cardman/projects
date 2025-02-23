package aiki.beans;

import code.bean.nat.*;
public class WelcomeBeanClickItems implements NatCaller, IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickItems() {
        this(null);
    }

    public WelcomeBeanClickItems(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)(re(new PokemonBeanStruct(getBean()),new NaSt[0]))).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance()).clickItems());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
