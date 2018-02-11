package aiki.beans.simulation;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.comparators.ComparatorStatistic;
import aiki.beans.facade.simulation.dto.EvLine;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;

public class EditPokemonBean extends CommonBean {

    @Accessible
    private String namePk = DataBase.EMPTY_STRING;

    @Accessible
    private short level;

    @Accessible
    private Rate experience;

    @Accessible
    private short happiness;

    @Accessible
    private boolean heal;

    @Accessible
    private Rate remainingHp;

    @Accessible
    private TreeMap<Statistic, EvLine> ev;

    @Accessible
    private String item = DataBase.EMPTY_STRING;

    @Accessible
    private CustList<SelectLineMove> moves = new CustList<SelectLineMove>();

    @Accessible
    private String ball;

    @Accessible
    private TreeMap<String,String> balls;

    @Override
    public void beforeDisplaying() {
        ev = new TreeMap<Statistic, EvLine>(new ComparatorStatistic());
        DataBase data_ = (DataBase) getDataBase();
        heal = false;
        ball = (String) getForms().getVal(CATCHING_BALL);
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        balls = new TreeMap<String, String>(new ComparatorTrStrings(translationsItems_));
        for (String b: data_.getItems().getKeys()) {
            if (data_.getItems().getVal(b) instanceof Ball) {
                balls.put(b, translationsItems_.getVal(b));
            }
        }
        namePk = (String) getForms().getVal(POKEMON_NAME_EDIT);
        level = (Short) getForms().getVal(POKEMON_LEVEL_EDIT);
        experience = (Rate) getForms().getVal(POKEMON_EXPERIENCE);
        happiness = (Short) getForms().getVal(POKEMON_HAPPINESS);
        item = (String) getForms().getVal(ITEM_EDIT);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            EvLine ev_ = new EvLine();
            ev_.setEv((Short) getForms().getVal(StringList.concat(POKEMON_EV_VAR,s.name())));
            ev.put(s, ev_);
        }
        remainingHp = (Rate) getForms().getVal(POKEMON_HP);
        StringList currentMoves_ = (StringList) getForms().getVal(POKEMON_MOVES_EDIT);
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

    @Accessible
    private String chooseItem() {
        getForms().put(IS_POKEMON_PLAYER_MOVES, true);
        getForms().put(ITEMS_SET_EDIT, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private String translateName() {
        if (namePk.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(namePk);
    }

    @Accessible
    private String translateItem() {
        if (item.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translationsItems_.getVal(item);
    }

    @Accessible
    private String addMoves() {
        getForms().put(IS_POKEMON_PLAYER_MOVES, true);
        getForms().put(MOVES_SET, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private String getTranslatedStatistic(Long _index) {
        Statistic st_ = ev.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic, String> tr_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return tr_.getVal(st_);
    }

    @Accessible
    private static String cancel() {
        return SIMULATION;
    }

    @Accessible
    private void deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(POKEMON_MOVES_EDIT, keptMoves_);
    }

    @Accessible
    private String edit() {
        getForms().put(ITEM_EDIT, item);
        getForms().put(POKEMON_EXPERIENCE, experience);
        getForms().put(POKEMON_HAPPINESS, happiness);
        getForms().put(POKEMON_HP, remainingHp);
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            getForms().put(StringList.concat(POKEMON_EV_VAR,s.name()), ev.getVal(s).getEv());
        }
        getForms().put(HEAL_EDIT_PK, heal);
        getForms().put(CATCHING_BALL, ball);
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
        DataBase data_ = (DataBase) getDataBase();
        if (selected_.size() > data_.getNbMaxMoves()) {
            return DataBase.EMPTY_STRING;
        }
        getForms().put(POKEMON_MOVES_EDIT, selected_);
        return SIMULATION;
    }
}
