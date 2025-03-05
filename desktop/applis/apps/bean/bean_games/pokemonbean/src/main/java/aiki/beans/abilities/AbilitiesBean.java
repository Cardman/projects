package aiki.beans.abilities;

import aiki.beans.*;
import aiki.facade.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.StringMap;

public final class AbilitiesBean extends AbilitySearchBean {
    private IntBeanChgSubmit updateValues;
    public AbilitiesBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataAbilityAbilities.M_P_0_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_INDEX);
//        initPage();
        initFormAb();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_OK));
        getUpdateValues().addEvt(new AbilitiesBeanSearch(this));
        feedParents();
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,sortedAbilitiesGet());
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_INDEX);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.ABILITIES).getMapping();
    }
    public String search() {
        return searchAbility(CST_ABILITY, PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML);
    }
    public String clickAbility(int _index) {
        return tryRedirect(sortedAbilitiesGet().get(_index));
    }

}