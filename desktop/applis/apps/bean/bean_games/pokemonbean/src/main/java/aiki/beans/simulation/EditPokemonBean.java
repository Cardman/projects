package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.EvLine;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.beans.moves.MovesBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class EditPokemonBean extends CommonBean {
    private String namePk = DataBase.EMPTY_STRING;
    private long level;
    private Rate experience;
    private long happiness;
    private boolean heal;
    private Rate remainingHp;
    private IdMap<Statistic, EvLine> ev;
    private String item = DataBase.EMPTY_STRING;
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private String ball;
    private DictionaryComparator<String,String> balls;

    @Override
    public void beforeDisplaying() {
        ev = new IdMap<Statistic, EvLine>();
        DataBase data_ = getDataBase();
        heal = false;
        ball = getForms().getValStr(CST_CATCHING_BALL);
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
        experience = getForms().getValRate(CST_POKEMON_EXPERIENCE);
        happiness = getForms().getValLong(CST_POKEMON_HAPPINESS);
        item = getForms().getValStr(CST_ITEM_EDIT);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            EvLine ev_ = new EvLine();
            ev_.setEv(getForms().getValLong(StringUtil.concat(CST_POKEMON_EV_VAR, s.getStatName())));
            ev.put(s, ev_);
        }
        remainingHp = getForms().getValRate(CST_POKEMON_HP);
        StringList currentMoves_ = getForms().getValList(CST_POKEMON_MOVES_EDIT);
        moves.clear();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
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
            moves.add(MovesBean.buildLine(translationsMoves_,translationsTypes_,translationsCategories_,k,moveData_,getDataBase()));
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String chooseItem() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, true);
        getForms().putItems(CST_ITEMS_SET_EDIT, new StringMap<Item>());
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
        getForms().putMoves(CST_MOVES_EDIT_SET, new StringMap<MoveData>());
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
        getForms().put(CST_POKEMON_EXPERIENCE, experience.absNb());
        getForms().put(CST_POKEMON_HAPPINESS, NumberUtil.max(happiness,0));
        getForms().put(CST_POKEMON_HP, remainingHp.absNb());
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            getForms().put(StringUtil.concat(CST_POKEMON_EV_VAR,s.getStatName()), NumberUtil.max(ev.getVal(s).getEv(),0));
        }
        getForms().put(CST_HEAL_EDIT_PK, heal);
        getForms().put(CST_CATCHING_BALL, ball);
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

    public void setExperience(Rate _experience) {
        experience = _experience;
    }

    public Rate getExperience() {
        return experience;
    }

    public DictionaryComparator<String,String> getBalls() {
        return balls;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String _ball) {
        ball = _ball;
    }

    public void setHappiness(long _happiness) {
        happiness = _happiness;
    }

    public long getHappiness() {
        return happiness;
    }

    public void setRemainingHp(Rate _remainingHp) {
        remainingHp = _remainingHp;
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    public void setHeal(boolean _heal) {
        heal = _heal;
    }

    public boolean getHeal() {
        return heal;
    }

    public IdMap<Statistic,EvLine> getEv() {
        return ev;
    }
}