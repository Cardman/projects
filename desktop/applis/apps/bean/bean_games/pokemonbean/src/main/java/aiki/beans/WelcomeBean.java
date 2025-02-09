package aiki.beans;
import aiki.beans.facade.simulation.enums.*;
import aiki.comparators.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public class WelcomeBean extends CommonBean implements BeanRenderWithAppName {

    public WelcomeBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade,_form);
        initPage();
        setTitledBorder(file().getVal(MessagesDataIndex.M_P_15_TITLE));
        element(MessagesDataIndex.M_P_15_GENERAL);
        element(MessagesDataIndex.M_P_15_ROUND);
        element(MessagesDataIndex.M_P_15_POKEDEX);
        element(MessagesDataIndex.M_P_15_ITEMS);
        element(MessagesDataIndex.M_P_15_MOVES);
        element(MessagesDataIndex.M_P_15_ABILITIES);
        element(MessagesDataIndex.M_P_15_STATUS);
        element(MessagesDataIndex.M_P_15_COMBOS);
        element(MessagesDataIndex.M_P_15_ENDROUND);
        element(MessagesDataIndex.M_P_15_MAP);
        element(MessagesDataIndex.M_P_15_SOLUTION);
        element(MessagesDataIndex.M_P_15_SIMULATION);
        element(MessagesDataIndex.M_P_15_LANGS);
        feedParents();
    }

    private void element(String _key) {
        nextPart();
        initLine();
        paintMetaLabelDisk();
        formatMessage(MessagesPkBean.INDEX, _key);
        feedParents();
        breakLine();
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.INDEX).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        getForms().put(CST_SIMULATION_STATE, SimulationSteps.DIFF);
        DictionaryComparator<TranslatedKey, MoveData> data_ = DictionaryComparatorUtil.buildMovesData();
        for (EntryCust<String,MoveData> e: getDataBase().getView().entryList()) {
            data_.addEntry(buildMv(getDataBase().getTranslatedMoves().getVal(getLanguage()),e.getKey()),e.getValue());
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