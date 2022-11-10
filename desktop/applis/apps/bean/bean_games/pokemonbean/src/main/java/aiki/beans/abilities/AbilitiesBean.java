package aiki.beans.abilities;

public class AbilitiesBean extends AbilitySearchBean {

    public String search() {
        return searchAbility(CST_ABILITY, AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML, AikiBeansAbilitiesStd.WEB_HTML_ABILITY_ABILITIES_HTML);
    }
    public String clickAbility(int _index) {
        getForms().put(CST_ABILITY, getSortedAbilities().get(_index));
        return AikiBeansAbilitiesStd.WEB_HTML_ABILITY_DATA_HTML;
    }

}