package aiki.beans.abilities;

public class AbilitiesBean extends AbilitySearchBean {

    public String search() {
        return searchAbility(CST_ABILITY);
    }
    public String clickAbility(int _index) {
        getForms().put(CST_ABILITY, getSortedAbilities().get(_index));
        return CST_ABILITY;
    }

}