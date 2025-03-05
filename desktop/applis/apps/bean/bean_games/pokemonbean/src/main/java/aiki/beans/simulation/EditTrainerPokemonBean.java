package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.*;
import aiki.beans.moves.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.fight.moves.*;
import aiki.map.pokemon.enums.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class EditTrainerPokemonBean extends CommonBean implements BeanRenderWithAppName {
    private final CrudPkCommon common = new CrudPkCommon();
    private boolean add;
    private String namePk = DataBase.EMPTY_STRING;
    private String ability = DataBase.EMPTY_STRING;
//    private String gender = Gender.NO_GENDER.getGenderName();
//    private int level;
//    private DictionaryComparator<String,String> genders;
    private String item = DataBase.EMPTY_STRING;
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private boolean allyPk;
    private IntBeanChgSubmit cancel;
    private IntBeanChgSubmit chooseName;
    private IntBeanChgSubmit chooseItem;
    private IntBeanChgSubmit chooseAbility;
    public EditTrainerPokemonBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_EDIT_POKEMON_TR));
        cancel = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_CANCEL));
        getCancel().addEvt(new EditTrainerPokemonBeanCancel(this));
        chooseName = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NAME_PK));
        getChooseName().addEvt(new EditTrainerPokemonBeanChooseName(this));
        chooseItem = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_PK));
        getChooseItem().addEvt(new EditTrainerPokemonBeanChooseItem(this));
        chooseAbility = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ABILITY_PK));
        getChooseAbility().addEvt(new EditTrainerPokemonBeanChooseAbility(this));
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NAME_PK);
        formatMessageDir(getTranslatedName());
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ABILITY_PK);
        formatMessageDir(getTranslatedAbility());
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_PK);
        formatMessageDir(getTranslatedItem());
    }

    public IntBeanChgSubmit getCancel() {
        return cancel;
    }

    public IntBeanChgSubmit getChooseName() {
        return chooseName;
    }

    public IntBeanChgSubmit getChooseItem() {
        return chooseItem;
    }

    public IntBeanChgSubmit getChooseAbility() {
        return chooseAbility;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        add = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.ADD;

        namePk = getForms().getValStr(CST_POKEMON_NAME_EDIT);
        item = getForms().getValStr(CST_ITEM_EDIT);
        ability = getForms().getValStr(CST_POKEMON_ABILITY_EDIT);
        common.setGender(getForms().getValGen(CST_POKEMON_GENDER_EDIT).getGenderName());
        common.setLevel(getForms().getValLong(CST_POKEMON_LEVEL_EDIT));

        moves.clear();
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: getForms().getValList(CST_POKEMON_MOVES_EDIT)) {
            MoveData moveData_ = data_.getMoves().getVal(k);
//            SelectLineMove line_ = new SelectLineMove();
//            line_.setName(k);
//            line_.setDisplayName(translationsMoves_.getVal(k));
//            StringList types_ = new StringList();
//            for (String t: moveData_.getTypes()) {
//                types_.add(translationsTypes_.getVal(t));
//            }
//            line_.setTypes(types_);
//            line_.setPp(moveData_.getPp());
//            line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
//            if (moveData_ instanceof DamagingMoveData) {
//                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
//                line_.setDirect(damag_.isDirect());
//                line_.setDamageMove(true);
//            } else {
//                line_.setDamageMove(false);
//            }
//            line_.setPriority(moveData_.getPriority());
//            line_.setSelected(false);
            moves.add(MovesBean.buildLine(translationsTypes_,translationsCategories_,buildMv(getFacade(),k),moveData_,getDataBase()));
        }
        moves.sortElts(new ComparatorMoves());
//        Map<SelectedBoolean,String> translatedBooleans_;
//        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
//        booleans = new TreeMap<new>(new ComparatorTrString<>(translatedBooleans_));
//        booleans.putAllMap(translatedBooleans_);
//        Map<String,String> translatedAbilities_;
//        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        abilities = new TreeMap<new>(new ComparatorTrString<>(translatedAbilities_));
        AbsMap<Gender,String> translatedGenders_;
        translatedGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        genders = DictionaryComparatorUtil.buildGenderStr(data_,getLanguage());
        common.init(data_,getLanguage());
        StringMap<String> translated_;
        translated_ = new StringMap<String>();
        for (EntryCust<Gender,String> s: translatedGenders_.entryList()) {
            translated_.addEntry(s.getKey().getGenderName(),s.getValue());
        }
        common.getGenders().addAllEntries(translated_);
        common.updateGender();
    }
    public String cancel() {
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String chooseAbility() {
        getForms().putAbilities(CST_ABILITIES_SET, DictionaryComparatorUtil.buildAbilitiesData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML;
    }
    public String chooseItem() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, false);
        getForms().putItems(CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML;
    }
    public String chooseName() {
        getForms().putPokedex(CST_POKEMON_SET, DictionaryComparatorUtil.buildPkData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML;
    }
    public String addMoves() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, false);
        getForms().putMoves(CST_MOVES_EDIT_SET, DictionaryComparatorUtil.buildMovesData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML;
    }
    public String deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, keptMoves_);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String validateTrainerPk() {
        DataBase data_ = getDataBase();
        common.patchLevel(data_);
//        if (level <= data_.getMinLevel()) {
//            level = (short) data_.getMinLevel();
//        }
//        if (level > data_.getMaxLevel()) {
//            level = (short) data_.getMaxLevel();
//        }
//        if (ability.isEmpty()) {
//            ability = data_.getMap().getFirstPokemon().getAbility();
//        }
//        if (namePk.isEmpty()) {
//            namePk = data_.getMap().getFirstPokemon().getName();
//        }
        StringList selected_ = new StringList();
        for (SelectLineMove s: moves) {
            selected_.add(s.getName());
        }
        if (selected_.isEmpty()) {
            selected_ = data_.getPokemon(namePk).getMovesBeforeLevel(common.getLevel());
        }
        if (add) {
            getForms().put(CST_POKEMON_FOE, !allyPk);
        }
        getForms().put(CST_POKEMON_NAME_EDIT, namePk);
        getForms().put(CST_POKEMON_LEVEL_EDIT, common.getLevel());
        getForms().put(CST_ITEM_EDIT, item);
        getForms().put(CST_POKEMON_GENDER_EDIT, PokemonStandards.getGenderByName(common.getGender()));
        getForms().put(CST_POKEMON_MOVES_EDIT, selected_);
        getForms().put(CST_POKEMON_ABILITY_EDIT, ability);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String getTranslatedName() {
        DataBase data_ = getDataBase();
        return data_.translatePokemon(namePk);
    }
    public String getTranslatedAbility() {
        DataBase data_ = getDataBase();
        return data_.translateAbility(ability);
    }
    public String getTranslatedItem() {
        DataBase data_ = getDataBase();
        return data_.translateItem(item);
    }

    public CustList<SelectLineMove> getMoves() {
        return moves;
    }

    public CrudPkCommon getCommon() {
        return common;
    }

    public boolean getAdd() {
        return add;
    }

    public void setAllyPk(boolean _allyPk) {
        allyPk = _allyPk;
    }

    public boolean getAllyPk() {
        return allyPk;
    }
}