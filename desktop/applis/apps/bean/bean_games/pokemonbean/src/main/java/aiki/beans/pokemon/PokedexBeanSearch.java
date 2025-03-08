package aiki.beans.pokemon;

import aiki.beans.*;
public final class PokedexBeanSearch implements IntBeanAction {
    private final PokedexBean bean;

    public PokedexBeanSearch(PokedexBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.search();
    }

}
