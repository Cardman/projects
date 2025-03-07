package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanChooseName implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanChooseName(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.chooseName();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
