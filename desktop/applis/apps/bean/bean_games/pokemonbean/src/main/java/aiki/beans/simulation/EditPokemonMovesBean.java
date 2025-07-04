package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.game.DifficultyBeanForm;
import aiki.beans.moves.MovesBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.MoveData;
import aiki.game.fight.FightSimulation;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class EditPokemonMovesBean extends WithFilterBean {
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private boolean player;
    private IntBeanChgBool availableMovesOnly = new BeanChgBool();
    private IntBeanChgSubmit updateValues;
    private IntBeanChgSubmit addMvs;

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_SEARCH_MOVES));
        formatMessageAnc(new EditPokemonMovesBeanCancel(this),MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_CANCEL);
        initFormMv(true);
        if (player) {
            initLine();
            formatMessage(MessagesPkBean.SIMU, MessagesDataSimulation.M_P_86_AVAILABLE_MOVES);
            setAvailableMovesOnly(DifficultyBeanForm.check(getBuilder().getGenInput(),this,getAvailableMovesOnly().genericValue()));
            feedParents();
        }
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_OK));
        getUpdateValues().addEvt(new EditPokemonMovesBeanSearch(this));
        feedParents();
        new BeanDisplayListGrid<SelectLineMove>(new BeanDisplaySelectLineMove()).displayGrid(this,getMoves(),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
        initLine();
        addMvs = getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD));
        getAddMvs().addEvt(new EditPokemonMovesBeanAddMoves(this));
        feedParents();
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public IntBeanChgSubmit getAddMvs() {
        return addMvs;
    }

    @Override
    public void beforeDisplaying() {
        player = getForms().getValBool(SIMU_CST_IS_POKEMON_PLAYER_MOVES);
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        getCategories().putAllMap(translationsCategories_);
        getCategories().put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        moves.clear();
        for (EntryCust<TranslatedKey, MoveData> k: getForms().getValMoveData(SIMU_CST_MOVES_EDIT_SET).entryList()) {
            MoveData moveData_ = k.getValue();
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
//            moves.add(line_);
            moves.add(MovesBean.buildLine(translationsTypes_,translationsCategories_,k.getKey(),moveData_,getDataBase()));
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String cancel() {
        getForms().putMoves(SIMU_CST_MOVES_EDIT_SET, DictionaryComparatorUtil.buildMovesData());
        return redirect();
    }
    public String addMoves() {
        StringList currentMoves_ = getForms().getValList(SIMU_CST_POKEMON_MOVES_EDIT);
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        currentMoves_.addAllElts(keptMoves_);
        currentMoves_.removeDuplicates();
        getForms().put(SIMU_CST_POKEMON_MOVES_EDIT, currentMoves_);
        return redirect();
    }
    public String search() {
        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        AbsMap<TranslatedKey, MoveData> moves_;
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        moves_ = new StringList();
        StringMap<MoveData> set_ = new StringMap<MoveData>();
        if (player) {
            if (availableMovesOnly.isSelected()) {
                String namePk_ = getForms().getValStr(SIMU_CST_POKEMON_NAME_EDIT);
                long level_ = getForms().getValLong(SIMU_CST_POKEMON_LEVEL_EDIT);
                set_.addAllEntries(FightSimulation.possiblesInitialMoves(namePk_, level_, data_));
            } else {
                set_.addAllEntries(data_.getMoves());
            }
        } else {
            set_.addAllEntries(data_.getMoves());
        }
        moves_ = movesAmong(set_);
//        for (EntryCust<String, MoveData> k: set_.entryList()) {
//            String displayName_ = translationsMoves_.getVal(k.getKey());
//            if (!StringUtil.match(displayName_, getTypedName())) {
//                continue;
//            }
//            MoveData moveData_ = k.getValue();
//            if (atLeastMatchType(translationsTypes_,moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), moveData_.getCategory()))) {
//                moves_.add(k.getKey());
//            }
//        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        getForms().putMoves(SIMU_CST_MOVES_EDIT_SET, moves_);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML;
    }

    private String redirect() {
        if (player) {
            return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }

    public boolean getPlayer() {
        return player;
    }

    public void setAvailableMovesOnly(IntBeanChgBool _availableMovesOnly) {
        availableMovesOnly = _availableMovesOnly;
    }

    public IntBeanChgBool getAvailableMovesOnly() {
        return availableMovesOnly;
    }

    public CustList<SelectLineMove> getMoves() {
        return moves;
    }
}