package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonMovesBeanAddMoves implements IntBeanAction {
    private final EditPokemonMovesBean bean;

    public EditPokemonMovesBeanAddMoves(EditPokemonMovesBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.addMoves();
    }

}
