package aiki.beans.simulation;

import aiki.beans.WithFilterBean;

public class SelectPokemonBean extends WithFilterBean {

    @Override
    public void beforeDisplaying() {
        bools();
        setupPokedex(getForms().getValPokemonData(CST_POKEMON_SET));
    }
    public static String cancel() {
        return AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String search() {
        return search(CST_POKEMON_NAME_EDIT, AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, AikiBeansSimulationStd.WEB_HTML_SIMULATION_SELECTPOKEMON_HTML);
    }

    public String clickLink(int _number) {
        getForms().put(CST_POKEMON_NAME_EDIT, getPokedex().get(_number).getName());
        return AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }

}