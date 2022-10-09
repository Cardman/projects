package aiki.beans.simulation;

import aiki.beans.abilities.AbilitySearchBean;

public class SelectAbilityBean extends AbilitySearchBean {

    public static String cancel() {
        return CST_ABILITY;
    }
    public String search() {
        return searchAbility(CST_POKEMON_ABILITY_EDIT);
    }

    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, getSortedAbilities().get(_index));
        return CST_ABILITY;
    }


}