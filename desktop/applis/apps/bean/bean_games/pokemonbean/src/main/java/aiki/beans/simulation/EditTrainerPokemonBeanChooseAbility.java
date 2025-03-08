package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanChooseAbility implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanChooseAbility(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.chooseAbility();
    }

}
