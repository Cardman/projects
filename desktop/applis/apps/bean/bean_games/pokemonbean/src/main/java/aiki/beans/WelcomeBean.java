package aiki.beans;
import aiki.beans.facade.simulation.enums.*;
import aiki.comparators.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class WelcomeBean extends CommonBean implements BeanRenderWithAppName {

    public WelcomeBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade,_form);
        setTitledBorder(file().getVal(MessagesDataIndex.M_P_15_TITLE));
        initPage();
        elementAnchor(MessagesDataIndex.M_P_15_GENERAL,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_GENERAL_GENERAL_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_ROUND,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_ROUND_HELPROUND_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_POKEDEX,new WelcomeBeanClickPokedex(this));
        elementAnchor(MessagesDataIndex.M_P_15_ITEMS,new WelcomeBeanClickItems(this));
        elementAnchor(MessagesDataIndex.M_P_15_MOVES,new WelcomeBeanSeeAllMoves(this));
        elementAnchor(MessagesDataIndex.M_P_15_ABILITIES,new WelcomeBeanClickAbilities(this));
        elementAnchor(MessagesDataIndex.M_P_15_STATUS,new WelcomeBeanClickStatus(this));
        elementAnchor(MessagesDataIndex.M_P_15_COMBOS,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_ENDROUND,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_MAP,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_SOLUTION,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_SOLUTION_SOLUTION_HTML,this));
        elementAnchor(MessagesDataIndex.M_P_15_LANGS,new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_LANGS_LANGS_HTML,this));
        feedParents();
    }

    private void elementAnchor(String _key, IntBeanAction _ac) {
        initLine();
        paintMetaLabelDisk();
        formatMessageAnc(_ac,MessagesPkBean.INDEX, _key);
        feedParents();
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.INDEX).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        DictionaryComparator<TranslatedKey, MoveData> data_ = DictionaryComparatorUtil.buildMovesData();
        for (EntryCust<String,MoveData> e: getDataBase().getView().entryList()) {
            data_.addEntry(buildMv(getFacade(),e.getKey()),e.getValue());
        }
        getForms().putMoves(CST_LEARNT_MOVES,data_);
    }

    public String seeAllMoves() {
        getForms().putMoves(CST_MOVES_SET, DictionaryComparatorUtil.buildMovesData());
        getForms().removeKey(CST_LEARNT);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, DictionaryComparatorUtil.buildMovesData());
        getForms().put(CST_LEARNT, true);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeNotLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, DictionaryComparatorUtil.buildMovesData());
        getForms().put(CST_LEARNT, false);
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String clickAbilities() {
        getForms().putAbilities(CST_ABILITIES_SET, DictionaryComparatorUtil.buildAbilitiesData());
        return PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML;
    }
    public String clickStatus() {
        getForms().putStatus(CST_STATUS_SET, DictionaryComparatorUtil.buildStatusData());
        return PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public String clickItems() {
        getForms().putItems(CST_ITEMS_SET, DictionaryComparatorUtil.buildItemsData());
        return PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }
    public String clickPokedex() {
        getForms().putPokedex(CST_POKEMON_SET, DictionaryComparatorUtil.buildPkData());
        return PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML;
    }
}