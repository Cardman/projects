package aiki.beans.abilities;

import aiki.beans.WithFilterBean;

public abstract class AbilitySearchBean extends WithFilterBean {
    @Override
    public void beforeDisplaying() {
        setSortedAbilities(getForms().getValAbilityData(CST_ABILITIES_SET));
//        typedAbility = StringList.replace(typedAbility, QUOTE, ESCAPED_QUOTE);
        escapeInputs();
    }
}
