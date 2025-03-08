package aiki.beans.simulation;

import aiki.beans.*;

public final class EditTrainerPokemonBeanDeleteMoves implements IntBeanAction {
    private final EditTrainerPokemonBean bean;

    public EditTrainerPokemonBeanDeleteMoves(EditTrainerPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.deleteMoves();
    }

}
