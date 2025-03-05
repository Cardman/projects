package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class EditTrainerPokemonBeanChooseName implements NatCaller, IntBeanAction {
    private final EditTrainerPokemonBean bean;
    public EditTrainerPokemonBeanChooseName() {
        this(null);
    }
    public EditTrainerPokemonBeanChooseName(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).chooseName());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
