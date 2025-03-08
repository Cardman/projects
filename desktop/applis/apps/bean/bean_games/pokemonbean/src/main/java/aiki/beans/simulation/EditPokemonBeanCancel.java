package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonBeanCancel implements IntBeanAction {
    private final EditPokemonBean bean;

    public EditPokemonBeanCancel(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancel();
    }

}
