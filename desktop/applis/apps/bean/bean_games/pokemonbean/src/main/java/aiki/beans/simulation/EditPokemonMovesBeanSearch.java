package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonMovesBeanSearch implements IntBeanAction {
    private final EditPokemonMovesBean bean;

    public EditPokemonMovesBeanSearch(EditPokemonMovesBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
