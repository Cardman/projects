package aiki.beans.simulation;

import aiki.beans.*;

public final class AddPokemonBeanClickLink implements IntBeanAction {
    private final AddPokemonBean bean;
    private final String index;
    public AddPokemonBeanClickLink(AddPokemonBean _b, String _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.putName(index);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
