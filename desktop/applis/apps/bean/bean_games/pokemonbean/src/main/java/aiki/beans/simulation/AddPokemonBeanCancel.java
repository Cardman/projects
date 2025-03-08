package aiki.beans.simulation;

import aiki.beans.*;
public final class AddPokemonBeanCancel implements IntBeanAction {
    private final AddPokemonBean bean;

    public AddPokemonBeanCancel(AddPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.cancel();
    }

}
