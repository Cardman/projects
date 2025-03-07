package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanCancel implements IntBeanAction{

    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanCancel(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancel();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
