package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.EvLine;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.beans.game.DifficultyBeanForm;
import aiki.beans.moves.MovesBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.MoveData;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class EditPokemonBean extends CommonBean implements BeanRenderWithAppName {
    private String namePk = DataBase.EMPTY_STRING;
    private long level;
    private IntBeanChgRate experience = new BeanChgRate();
    private IntBeanChgLong happiness = new BeanChgLong();
    private IntBeanChgBool heal = new BeanChgBool();
    private IntBeanChgRate remainingHp = new BeanChgRate();
    private IdMap<Statistic, EvLine> ev;
    private String item = DataBase.EMPTY_STRING;
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private IntBeanChgString ball = new BeanChgString();
    private DictionaryComparator<String,String> balls;
    public EditPokemonBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_EDIT_POKEMON_PL));
        formatMessageAnc(new EditPokemonBeanCancel(this),MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_CANCEL);
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NAME_PK);
        formatMessageDir(translateName());
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_LEVEL_PK);
        formatMessageDir(Long.toString(level));
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_PK);
        formatMessageDir(translateItem());
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ITEM_PK)).addEvt(new EditPokemonBeanChooseItem(this));
//        if (!namePk.isEmpty()) {
//            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD)).addEvt(new EditPokemonBeanAddMoves(this));
//            new BeanDisplayListGrid<SelectLineMove>(new BeanDisplaySelectLineMove()).displayGrid(this,getMoves(),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
//            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD)).addEvt(new EditPokemonBeanDeleteMoves(this));
//            initLine();
//            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EXP_PK);
//            setExperience(DifficultyBeanForm.rate(getBuilder().getGenInput(), this,getExperience().valueRate()));
//            feedParents();
//            initLine();
//            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CATCHING_BALL);
//            setBall(DifficultyBeanForm.select(getBuilder().getGenInput(), this,balls,getBall().tryRet()));
//            feedParents();
//            initLine();
//            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HAPPINESS_PK);
//            setHappiness(DifficultyBeanForm.iv(getBuilder().getGenInput(), this,getHappiness().valueLong()));
//            feedParents();
//            initLine();
//            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMAINING_HP);
//            setRemainingHp(DifficultyBeanForm.rate(getBuilder().getGenInput(), this,getRemainingHp().valueRate()));
//            feedParents();
//            initLine();
//            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HEAL_HP);
//            setHeal(DifficultyBeanForm.check(getBuilder().getGenInput(), this,getHeal().isSelected()));
//            feedParents();
//            for (EntryCust<Statistic,EvLine> l:ev.entryList()) {
//                initLine();
//                formatMessageDir(getFacade().getTranslatedStatistics().getVal(l.getKey()));
//                l.getValue().setEv(DifficultyBeanForm.iv(getBuilder().getGenInput(), this, l.getValue().getEv().valueLong()));
//                feedParents();
//            }
//            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EDIT)).addEvt(new EditPokemonBeanEdit(this));
//        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD)).addEvt(new EditPokemonBeanAddMoves(this));
        new BeanDisplayListGrid<SelectLineMove>(new BeanDisplaySelectLineMove()).displayGrid(this,getMoves(),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER,MessagesDataSimulation.M_P_86_SELECTED);
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMOVE)).addEvt(new EditPokemonBeanDeleteMoves(this));
        initLine();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EXP_PK);
        setExperience(DifficultyBeanForm.rate(getBuilder().getGenInput(), this,getExperience().valueRate()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CATCHING_BALL);
        setBall(DifficultyBeanForm.select(getBuilder().getGenInput(), this,balls,getBall().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HAPPINESS_PK);
        setHappiness(DifficultyBeanForm.iv(getBuilder().getGenInput(), this,getHappiness().valueLong()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_REMAINING_HP);
        setRemainingHp(DifficultyBeanForm.rate(getBuilder().getGenInput(), this,getRemainingHp().valueRate()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_HEAL_HP);
        setHeal(DifficultyBeanForm.check(getBuilder().getGenInput(), this,getHeal().isSelected()));
        feedParents();
        for (EntryCust<Statistic,EvLine> l:ev.entryList()) {
            initLine();
            formatMessageDir(getFacade().getTranslatedStatistics().getVal(l.getKey()));
            l.getValue().setEv(DifficultyBeanForm.iv(getBuilder().getGenInput(), this, l.getValue().getEv().valueLong()));
            feedParents();
        }
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_EDIT)).addEvt(new EditPokemonBeanEdit(this));
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        ev = new IdMap<Statistic, EvLine>();
        DataBase data_ = getDataBase();
        heal.setSelected(false);
        ball.setupValue(getForms().getValStr(CST_CATCHING_BALL));
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        balls = DictionaryComparatorUtil.buildItemsStr(data_,getLanguage());
        for (String b: data_.getItems().getKeys()) {
            if (data_.getItems().getVal(b) instanceof Ball) {
                balls.put(b, translationsItems_.getVal(b));
            }
        }
        namePk = getForms().getValStr(CST_POKEMON_NAME_EDIT);
        level = getForms().getValLong(CST_POKEMON_LEVEL_EDIT);
        experience.valueRate(getForms().getValRate(CST_POKEMON_EXPERIENCE));
        happiness.valueLong(getForms().getValLong(CST_POKEMON_HAPPINESS));
        item = getForms().getValStr(CST_ITEM_EDIT);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            EvLine ev_ = new EvLine();
            ev_.getEv().valueLong(getForms().getValLong(StringUtil.concat(CST_POKEMON_EV_VAR, s.getStatName())));
            ev.put(s, ev_);
        }
        remainingHp.valueRate(getForms().getValRate(CST_POKEMON_HP));
        StringList currentMoves_ = getForms().getValList(CST_POKEMON_MOVES_EDIT);
        moves.clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: currentMoves_) {
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
//            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
//            if (line_.isDamageMove()) {
//                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
//                line_.setDirect(damag_.isDirect());
//            }
//            line_.setPriority(moveData_.getPriority());
//            line_.setSelected(false);
            moves.add(MovesBean.buildLine(translationsTypes_,translationsCategories_,buildMv(getFacade(),k),moveData_,getDataBase()));
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String chooseItem() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, true);
        getForms().putItems(CST_ITEMS_SET_EDIT, DictionaryComparatorUtil.buildItemsData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML;
    }
    public String translateName() {
//        if (namePk.isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(namePk);
    }
    public String translateItem() {
//        if (item.isEmpty()) {
//            return DataBase.EMPTY_STRING;
//        }
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translationsItems_.getVal(item);
    }
    public String addMoves() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, true);
        getForms().putMoves(CST_MOVES_EDIT_SET, DictionaryComparatorUtil.buildMovesData());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML;
    }
    public String getTranslatedStatistic(int _index) {
        Statistic st_ = ev.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic, String> tr_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return tr_.getVal(st_);
    }
    public String cancel() {
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, keptMoves_);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
    }
    public String edit() {
        getForms().put(CST_ITEM_EDIT, item);
        getForms().put(CST_POKEMON_EXPERIENCE, experience.valueRate().absNb());
        getForms().put(CST_POKEMON_HAPPINESS, NumberUtil.max(happiness.valueLong(),0));
        getForms().put(CST_POKEMON_HP, remainingHp.valueRate().absNb());
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            getForms().put(StringUtil.concat(CST_POKEMON_EV_VAR,s.getStatName()), NumberUtil.max(ev.getVal(s).getEv().valueLong(),0));
        }
        getForms().put(CST_HEAL_EDIT_PK, heal.isSelected());
        getForms().put(CST_CATCHING_BALL, ball.tryRet());
        StringList selected_ = new StringList();
        for (SelectLineMove s: moves) {
//            if (s.isSelected()) {
//                selected_.add(s.getName());
//            }
            selected_.add(s.getName());
        }
        if (!inRangeMoves(selected_)) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, selected_);
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.EDIT);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public boolean inRangeMoves(StringList _selected) {
        DataBase data_ = getDataBase();
        return inRange(_selected.size(),1,data_.getNbMaxMoves());
    }

    public long getLevel() {
        return level;
    }

    public String getNamePk() {
        return namePk;
    }

    public CustList<SelectLineMove> getMoves() {
        return moves;
    }

    public void setExperience(IntBeanChgRate _experience) {
        experience = _experience;
    }

    public IntBeanChgRate getExperience() {
        return experience;
    }

    public DictionaryComparator<String,String> getBalls() {
        return balls;
    }

    public IntBeanChgString getBall() {
        return ball;
    }

    public void setBall(IntBeanChgString _ball) {
        ball = _ball;
    }

    public void setHappiness(IntBeanChgLong _happiness) {
        happiness = _happiness;
    }

    public IntBeanChgLong getHappiness() {
        return happiness;
    }

    public void setRemainingHp(IntBeanChgRate _remainingHp) {
        remainingHp = _remainingHp;
    }

    public IntBeanChgRate getRemainingHp() {
        return remainingHp;
    }

    public void setHeal(IntBeanChgBool _heal) {
        heal = _heal;
    }

    public IntBeanChgBool getHeal() {
        return heal;
    }

    public IdMap<Statistic,EvLine> getEv() {
        return ev;
    }
}