package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonBeanAddMoves implements IntBeanAction {
    private final EditPokemonBean bean;

    public EditPokemonBeanAddMoves(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.addMoves();
    }

}
