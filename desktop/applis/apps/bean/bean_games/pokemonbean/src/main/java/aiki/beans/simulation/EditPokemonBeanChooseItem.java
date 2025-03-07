package aiki.beans.simulation;

import aiki.beans.*;

public final class EditPokemonBeanChooseItem implements IntBeanAction {
    private final EditPokemonBean bean;

    public EditPokemonBeanChooseItem(EditPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.chooseItem();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
