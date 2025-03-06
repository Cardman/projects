package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class EditPokemonMovesBeanAddMoves implements NatCaller, IntBeanAction {
    private final EditPokemonMovesBean bean;
    public EditPokemonMovesBeanAddMoves() {
        this(null);
    }
    public EditPokemonMovesBeanAddMoves(EditPokemonMovesBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).addMoves());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
