package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectPokemonBeanSearch implements IntBeanAction {
    private final SelectPokemonBean bean;

    public SelectPokemonBeanSearch(SelectPokemonBean _b) {
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
