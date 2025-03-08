package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanValidateTrainerPk implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanValidateTrainerPk(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.validateTrainerPk();
    }

}
