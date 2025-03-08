package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanAddMoves implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanAddMoves(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.addMoves();
    }

}
