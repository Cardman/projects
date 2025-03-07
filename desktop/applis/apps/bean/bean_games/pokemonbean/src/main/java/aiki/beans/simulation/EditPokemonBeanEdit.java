package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonBeanEdit implements IntBeanAction {
    private final EditPokemonBean bean;

    public EditPokemonBeanEdit(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.edit();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
