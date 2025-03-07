package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonBeanDeleteMoves implements IntBeanAction {
    private final EditPokemonBean bean;

    public EditPokemonBeanDeleteMoves(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.deleteMoves();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
