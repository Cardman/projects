package aiki.beans.abilities;

import aiki.beans.*;
import aiki.beans.game.*;
import code.scripts.pages.aiki.*;

public abstract class AbilitySearchBean extends WithFilterBean {
    protected void initFormAb() {
        initLine();
        formatMessage(MessagesPkBean.ABILITIES, MessagesDataAbilityAbilities.M_P_0_CONTENT);
        setTypedAbility(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedAbility().tryRet()));
        feedParents();
    }
    @Override
    public void beforeDisplaying() {
        setSortedAbilities(getForms().getValAbilityData(CST_ABILITIES_SET));
//        typedAbility = StringList.replace(typedAbility, QUOTE, ESCAPED_QUOTE);
//        escapeInputs();
    }
}
