package aiki.beans.simulation;
import code.bean.Accessible;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.game.fight.FightSimulation;

public class EditPokemonMovesBean extends CommonBean {

    @Accessible
    private CustList<SelectLineMove> moves = new CustList<SelectLineMove>();

    @Accessible
    private StringList sortedMoves = new StringList();

    @Accessible
    private String category = DataBase.EMPTY_STRING;

    @Accessible
    private StringMap<String> categories = new StringMap<String>();

    @Accessible
    private String typedName = DataBase.EMPTY_STRING;

    @Accessible
    private String typedType = DataBase.EMPTY_STRING;

    @Accessible
    private boolean wholeWord;

    @Accessible
    private boolean player;

    @Accessible
    private boolean availableMovesOnly;

    @Override
    public void beforeDisplaying() {
        player = (Boolean) getForms().getVal(IS_POKEMON_PLAYER_MOVES);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        categories.putAllMap(translationsCategories_);
        categories.put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        moves.clear();
        for (String k: (StringList) getForms().getVal(MOVES_SET)) {
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
    private String cancel() {
        getForms().put(MOVES_SET, new StringList());
        if (player) {
            return EDIT_POKEMON_PLAYER;
        }
        return POKEMON_EDIT;
    }

    @Accessible
    private String addMoves() {
        StringList currentMoves_ = (StringList) getForms().getVal(POKEMON_MOVES_EDIT);
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        currentMoves_.addAllElts(keptMoves_);
        currentMoves_.removeDuplicates();
        getForms().put(POKEMON_MOVES_EDIT, currentMoves_);
        if (player) {
            return EDIT_POKEMON_PLAYER;
        }
        return POKEMON_EDIT;
    }

    @Accessible
    private void search() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        StringList set_ = new StringList();
        if (player) {
            if (availableMovesOnly) {
                String namePk_ = (String) getForms().getVal(POKEMON_NAME_EDIT);
                short level_ = (Short) getForms().getVal(POKEMON_LEVEL_EDIT);
                set_.addAllElts(FightSimulation.possiblesInitialMoves(namePk_, level_, data_));
            } else {
                set_.addAllElts(data_.getMoves().getKeys());
            }
        } else {
            set_.addAllElts(data_.getMoves().getKeys());
        }
        for (String k: set_) {
            String displayName_ = translationsMoves_.getVal(k);
            if (!StringList.match(displayName_, typedName)) {
                continue;
            }
            MoveData moveData_ = data_.getMoves().getVal(k);
            boolean atLeastMatchType_ = false;
            for (String t: moveData_.getTypes()) {
                String displayType_;
                displayType_ = translationsTypes_.getVal(t);
                if (wholeWord) {
                    if (typedType == null) {
                        continue;
                    }
                    if (!StringList.quickEq(displayType_, typedType)) {
                        continue;
                    }
                } else {
                    if (!StringList.match(displayType_, typedType)) {
                        continue;
                    }
                }
                atLeastMatchType_ = true;
            }
            if (!atLeastMatchType_) {
                continue;
            }
            if (!StringList.quickEq(category, DataBase.EMPTY_STRING)) {
                if (!StringList.quickEq(category, moveData_.getCategory())) {
                    continue;
                }
            }
            moves_.add(k);
        }
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        getForms().put(MOVES_SET, moves_);
    }
}
