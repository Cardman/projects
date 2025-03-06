package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public final class EditPokemonBeanChooseItem implements NatCaller, IntBeanAction {
    private final EditPokemonBean bean;
    public EditPokemonBeanChooseItem() {
        this(null);
    }
    public EditPokemonBeanChooseItem(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).chooseItem());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
