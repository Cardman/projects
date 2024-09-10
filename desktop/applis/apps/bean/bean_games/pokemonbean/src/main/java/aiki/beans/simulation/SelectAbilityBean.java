package aiki.beans.simulation;

import aiki.beans.abilities.AbilitySearchBean;
import code.scripts.confs.PkScriptPages;

public class SelectAbilityBean extends AbilitySearchBean {

    public static String cancel() {
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String search() {
        return searchAbility(CST_POKEMON_ABILITY_EDIT, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML);
    }

    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, getSortedAbilities().get(_index));
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }


}