package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.abilities.*;
import aiki.facade.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class SelectAbilityBean extends AbilitySearchBean {
    private IntBeanChgSubmit updateValues;
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_SELECT_ABILITY));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,this),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL);
        initFormAb();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.ABILITIES,MessagesDataAbilityAbilities.M_P_0_OK));
        getUpdateValues().addEvt(new SelectAbilityBeanSearch(this));
        CustList<TranslatedKey> ab_ = sortedAbilitiesGet();
        int size_ = ab_.size();
        for (int i = 0; i < size_; i++) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDirAnc(ab_.get(i).getTranslation(),new SelectAbilityBeanClickAbility(this,i));
            feedParents();
        }
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    public static String cancel() {
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String search() {
        return searchAbility(CST_POKEMON_ABILITY_EDIT, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML);
    }

    public String clickAbility(int _index) {
        getForms().put(CST_POKEMON_ABILITY_EDIT, sortedAbilitiesGet().get(_index).getKey());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }


}