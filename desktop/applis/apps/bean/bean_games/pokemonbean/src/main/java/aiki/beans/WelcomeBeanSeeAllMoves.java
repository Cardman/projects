package aiki.beans;

import code.bean.nat.*;
public final class WelcomeBeanSeeAllMoves implements NatCaller, IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanSeeAllMoves() {
        this(null);
    }

    public WelcomeBeanSeeAllMoves(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)(re(new PokemonBeanStruct(getBean()),new NaSt[0]))).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance()).seeAllMoves());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
