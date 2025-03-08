package aiki.beans.simulation;

import aiki.beans.*;
public final class AddPokemonBeanAdd implements IntBeanAction {
    private final AddPokemonBean bean;
    public AddPokemonBeanAdd(AddPokemonBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.add();
    }

}
