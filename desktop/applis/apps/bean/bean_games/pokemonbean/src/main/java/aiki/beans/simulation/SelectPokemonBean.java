package aiki.beans.simulation;

import aiki.beans.WithFilterBean;

public class SelectPokemonBean extends WithFilterBean {

    @Override
    public void beforeDisplaying() {
        bools();
        setupPokedex(getForms().getValList(CST_POKEMON_SET));
    }
    public static String cancel() {
        return CST_POKEMON;
    }
    public String search() {
        return search(CST_POKEMON_NAME_EDIT);
    }

    public String clickLink(int _number) {
        getForms().put(CST_POKEMON_NAME_EDIT, getPokedex().get(_number).getName());
        return CST_POKEMON;
    }

}