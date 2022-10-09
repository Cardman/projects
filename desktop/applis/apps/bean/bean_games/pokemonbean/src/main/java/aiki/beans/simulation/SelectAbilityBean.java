package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.db.DataBase;
import code.util.StringList;

public class SelectAbilityBean extends CommonBean {

    @Override
    public void beforeDisplaying() {
        setSortedAbilities(getForms().getValList(CST_ABILITIES_SET));
//        typedAbility = StringList.replace(typedAbility, QUOTE, ESCAPED_QUOTE);
        escapeInputs();
    }
    public static String cancel() {
        return CST_ABILITY;
    }
    public String search() {
        StringList sortedAbilities_ = sortedAbilities();
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_POKEMON_ABILITY_EDIT, sortedAbilities_.first());
            return CST_ABILITY;
        }
        getForms().put(CST_ABILITIES_SET, sortedAbilities_);
        return CST_ABILITIES;
    }

    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, getSortedAbilities().get(_index));
        return CST_ABILITY;
    }


}