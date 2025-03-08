package aiki.beans.pokemon;

import aiki.beans.*;
public final class PokemonBeanClickPokedex implements IntBeanAction {
    private final PokemonBean bean;

    public PokemonBeanClickPokedex(PokemonBean _p) {
        bean = _p;
    }

    @Override
    public String actionBean() {
        return bean.clickPokedex();
    }

}
