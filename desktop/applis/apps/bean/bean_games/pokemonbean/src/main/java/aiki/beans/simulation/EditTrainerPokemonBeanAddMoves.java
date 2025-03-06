package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class EditTrainerPokemonBeanAddMoves implements NatCaller, IntBeanAction {
    private final EditTrainerPokemonBean bean;
    public EditTrainerPokemonBeanAddMoves() {
        this(null);
    }
    public EditTrainerPokemonBeanAddMoves(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).addMoves());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
