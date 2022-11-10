package aiki.beans.simulation;

import aiki.beans.abilities.AbilitySearchBean;

public class SelectAbilityBean extends AbilitySearchBean {

    public static String cancel() {
        return AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String search() {
        return searchAbility(CST_POKEMON_ABILITY_EDIT, AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, AikiBeansSimulationStd.WEB_HTML_SIMULATION_SELECTABILITY_HTML);
    }

    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, getSortedAbilities().get(_index));
        return AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }


}