package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.comparators.ComparatorStatistic;
import aiki.beans.facade.simulation.dto.EvLine;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.StringUtil;

public class EditPokemonBean extends CommonBean {
    private String namePk = DataBase.EMPTY_STRING;
    private int level;
    private Rate experience;
    private int happiness;
    private boolean heal;
    private Rate remainingHp;
    private TreeMap<Statistic, EvLine> ev;
    private String item = DataBase.EMPTY_STRING;
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private String ball;
    private TreeMap<String,String> balls;

    @Override
    public void beforeDisplaying() {
        ev = new TreeMap<Statistic, EvLine>(new ComparatorStatistic());
        DataBase data_ = getDataBase();
        heal = false;
        ball = getForms().getValStr(CST_CATCHING_BALL);
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        balls = new TreeMap<String, String>(new ComparatorTrStrings(translationsItems_));
        for (String b: data_.getItems().getKeys()) {
            if (data_.getItems().getVal(b) instanceof Ball) {
                balls.put(b, translationsItems_.getVal(b));
            }
        }
        namePk = getForms().getValStr(CST_POKEMON_NAME_EDIT);
        level = getForms().getValInt(CST_POKEMON_LEVEL_EDIT);
        experience = getForms().getValRate(CST_POKEMON_EXPERIENCE);
        happiness = getForms().getValInt(CST_POKEMON_HAPPINESS);
        item = getForms().getValStr(CST_ITEM_EDIT);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            EvLine ev_ = new EvLine();
            ev_.setEv(getForms().getValInt(StringUtil.concat(CST_POKEMON_EV_VAR, s.name())));
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
            SelectLineMove line_ = new SelectLineMove();
            line_.setName(k);
            line_.setDisplayName(translationsMoves_.getVal(k));
            StringList types_ = new StringList();
            for (String t: moveData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setPp(moveData_.getPp());
            line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
            if (line_.isDamageMove()) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(false);
            moves.add(line_);
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String chooseItem() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, true);
        getForms().put(CST_ITEMS_SET_EDIT, new StringList());
        return CST_POKEMON_EDIT;
    }
    public String translateName() {
        if (namePk.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(namePk);
    }
    public String translateItem() {
        if (item.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translationsItems_.getVal(item);
    }
    public String addMoves() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, true);
        getForms().put(CST_MOVES_SET, new StringList());
        return CST_POKEMON_EDIT;
    }
    public String getTranslatedStatistic(int _index) {
        Statistic st_ = ev.getKey(_index);
        DataBase data_ = getDataBase();
        EnumMap<Statistic, String> tr_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return tr_.getVal(st_);
    }
    public static String cancel() {
        return CST_SIMULATION;
    }
    public void deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, keptMoves_);
    }
    public String edit() {
        getForms().put(CST_ITEM_EDIT, item);
        getForms().put(CST_POKEMON_EXPERIENCE, experience);
        getForms().put(CST_POKEMON_HAPPINESS, happiness);
        getForms().put(CST_POKEMON_HP, remainingHp);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            getForms().put(StringUtil.concat(CST_POKEMON_EV_VAR,s.name()), ev.getVal(s).getEv());
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
        if (selected_.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        if (selected_.size() > data_.getNbMaxMoves()) {
            return DataBase.EMPTY_STRING;
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, selected_);
        return CST_SIMULATION;
    }

    public int getLevel() {
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

    public TreeMap<String,String> getBalls() {
        return balls;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String _ball) {
        ball = _ball;
    }

    public void setHappiness(int _happiness) {
        happiness = _happiness;
    }

    public int getHappiness() {
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

    public TreeMap<Statistic,EvLine> getEv() {
        return ev;
    }
}