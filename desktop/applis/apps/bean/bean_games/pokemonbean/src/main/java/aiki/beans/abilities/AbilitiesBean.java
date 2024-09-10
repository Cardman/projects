package aiki.beans.abilities;

import code.scripts.confs.PkScriptPages;

public class AbilitiesBean extends AbilitySearchBean {

    public String search() {
        return searchAbility(CST_ABILITY, PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML);
    }
    public String clickAbility(int _index) {
        getForms().put(CST_ABILITY, getSortedAbilities().get(_index));
        return PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML;
    }

}