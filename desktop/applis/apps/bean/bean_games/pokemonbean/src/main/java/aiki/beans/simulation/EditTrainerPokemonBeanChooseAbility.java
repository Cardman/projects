package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class EditTrainerPokemonBeanChooseAbility implements NatCaller, IntBeanAction {
    private final EditTrainerPokemonBean bean;
    public EditTrainerPokemonBeanChooseAbility() {
        this(null);
    }
    public EditTrainerPokemonBeanChooseAbility(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).chooseAbility());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
