package aiki.beans.simulation;

import aiki.beans.*;

public final class AddPokemonBeanSearch implements IntBeanAction {
    private final AddPokemonBean bean;

    public AddPokemonBeanSearch(AddPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
