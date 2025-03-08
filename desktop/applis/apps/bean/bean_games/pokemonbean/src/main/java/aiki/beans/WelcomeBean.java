package aiki.beans;
import aiki.beans.facade.simulation.enums.*;
import aiki.comparators.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class WelcomeBean extends CommonBean implements BeanRenderWithAppName {

    public WelcomeBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataIndex.M_P_15_TITLE));
        initPage();
        elementAnchor(MessagesDataIndex.M_P_15_GENERAL,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_GENERAL_GENERAL_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_ROUND,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_ROUND_HELPROUND_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_POKEDEX,new WelcomeBeanClickPokedex(this));
        elementAnchor(MessagesDataIndex.M_P_15_ITEMS,new WelcomeBeanClickItems(this));
        elementAnchor(MessagesDataIndex.M_P_15_MOVES,new WelcomeBeanSeeAllMoves(this));
        elementAnchor(MessagesDataIndex.M_P_15_ABILITIES,new WelcomeBeanClickAbilities(this));
        elementAnchor(MessagesDataIndex.M_P_15_STATUS,new WelcomeBeanClickStatus(this));
        elementAnchor(MessagesDataIndex.M_P_15_COMBOS,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_ENDROUND,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_MAP,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_MAP_MAP_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_SOLUTION,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SOLUTION_SOLUTION_HTML));
        elementAnchor(MessagesDataIndex.M_P_15_LANGS,new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_LANGS_LANGS_HTML));
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
        return CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, DictionaryComparatorUtil.buildMovesData());
        getForms().put(CST_LEARNT, true);
        return CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String seeNotLearntMoves() {
        getForms().putMoves(CST_MOVES_SET, DictionaryComparatorUtil.buildMovesData());
        getForms().put(CST_LEARNT, false);
        return CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
    }
    public String clickAbilities() {
        getForms().putAbilities(CST_ABILITIES_SET, DictionaryComparatorUtil.buildAbilitiesData());
        return CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML;
    }
    public String clickStatus() {
        getForms().putStatus(CST_STATUS_SET, DictionaryComparatorUtil.buildStatusData());
        return CommonBean.REN_ADD_WEB_HTML_STATUS_STATUS_HTML;
    }
    public String clickItems() {
        getForms().putItems(CST_ITEMS_SET, DictionaryComparatorUtil.buildItemsData());
        return CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }
    public String clickPokedex() {
        getForms().putPokedex(CST_POKEMON_SET, DictionaryComparatorUtil.buildPkData());
        return CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML;
    }
}