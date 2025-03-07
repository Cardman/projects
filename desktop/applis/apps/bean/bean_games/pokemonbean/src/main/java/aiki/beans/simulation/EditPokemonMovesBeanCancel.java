package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonMovesBeanCancel implements IntBeanAction {
    private final EditPokemonMovesBean bean;

    public EditPokemonMovesBeanCancel(EditPokemonMovesBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancel();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
