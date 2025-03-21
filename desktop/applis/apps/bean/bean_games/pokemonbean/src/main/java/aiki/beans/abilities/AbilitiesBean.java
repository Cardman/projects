package aiki.beans.abilities;

import aiki.beans.*;
import aiki.facade.*;
import code.scripts.pages.aiki.*;
import code.util.StringMap;

public final class AbilitiesBean extends AbilitySearchBean {
    private IntBeanChgSubmit updateValues;
    public AbilitiesBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataAbilityAbilities.M_P_0_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_INDEX);
//        initPage();
        initFormAb();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_OK));
        getUpdateValues().addEvt(new AbilitiesBeanSearch(this));
        feedParents();
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,sortedAbilitiesGet());
        formatMessageAnc(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_INDEX);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.ABILITIES).getMapping();
    }
    public String search() {
        return searchAbility(CST_ABILITY, CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML);
    }
    public String clickAbility(int _index) {
        return tryRedirect(sortedAbilitiesGet().get(_index));
    }

}