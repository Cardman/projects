package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanChooseItem implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanChooseItem(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.chooseItem();
    }

}
