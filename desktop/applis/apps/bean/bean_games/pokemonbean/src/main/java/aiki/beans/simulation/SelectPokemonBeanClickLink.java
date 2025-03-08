package aiki.beans.simulation;

import aiki.beans.*;

public final class SelectPokemonBeanClickLink implements IntBeanAction {
    private final SelectPokemonBean bean;
    private final String index;
    public SelectPokemonBeanClickLink(SelectPokemonBean _b, String _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.putName(index);
    }

}
