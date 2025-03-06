package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public final class MoveBeanClickMoves implements NatCaller, IntBeanAction {
    private final MoveBean bean;
    public MoveBeanClickMoves() {
        this(null);
    }
    public MoveBeanClickMoves(MoveBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickMoves());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
