package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.pokemon.PokedexBean;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.game.fight.FightSimulation;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class EditPokemonMovesBean extends CommonBean {
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private String category = DataBase.EMPTY_STRING;
    private final StringMap<String> categories = new StringMap<String>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private boolean wholeWord;
    private boolean player;
    private boolean availableMovesOnly;

    @Override
    public void beforeDisplaying() {
        player = getForms().getValBool(CST_IS_POKEMON_PLAYER_MOVES);
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        categories.putAllMap(translationsCategories_);
        categories.put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        moves.clear();
        for (String k: getForms().getValList(CST_MOVES_SET)) {
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
            if (moveData_ instanceof DamagingMoveData) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
                line_.setDamageMove(true);
            } else {
                line_.setDamageMove(false);
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(false);
            moves.add(line_);
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String cancel() {
        getForms().put(CST_MOVES_SET, new StringList());
        if (player) {
            return CST_EDIT_POKEMON_PLAYER;
        }
        return CST_POKEMON_EDIT;
    }
    public String addMoves() {
        StringList currentMoves_ = getForms().getValList(CST_POKEMON_MOVES_EDIT);
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        currentMoves_.addAllElts(keptMoves_);
        currentMoves_.removeDuplicates();
        getForms().put(CST_POKEMON_MOVES_EDIT, currentMoves_);
        if (player) {
            return CST_EDIT_POKEMON_PLAYER;
        }
        return CST_POKEMON_EDIT;
    }
    public void search() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        StringList set_ = new StringList();
        if (player) {
            if (availableMovesOnly) {
                String namePk_ = getForms().getValStr(CST_POKEMON_NAME_EDIT);
                int level_ = getForms().getValInt(CST_POKEMON_LEVEL_EDIT);
                set_.addAllElts(FightSimulation.possiblesInitialMoves(namePk_, (short) level_, data_));
            } else {
                set_.addAllElts(data_.getMoves().getKeys());
            }
        } else {
            set_.addAllElts(data_.getMoves().getKeys());
        }
        for (String k: set_) {
            String displayName_ = translationsMoves_.getVal(k);
            if (!StringUtil.match(displayName_, typedName)) {
                continue;
            }
            MoveData moveData_ = data_.getMoves().getVal(k);
            if (PokedexBean.atLeastMatchType(translationsTypes_,wholeWord,typedType,moveData_.getTypes()) && (StringUtil.quickEq(category, DataBase.EMPTY_STRING) || StringUtil.quickEq(category, moveData_.getCategory()))) {
                moves_.add(k);
            }
        }
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        getForms().put(CST_MOVES_SET, moves_);
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public StringMap<String> getCategories() {
        return categories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public void setTypedType(String _typedType) {
        typedType = _typedType;
    }

    public String getTypedType() {
        return typedType;
    }

    public void setWholeWord(boolean _wholeWord) {
        wholeWord = _wholeWord;
    }

    public boolean getWholeWord() {
        return wholeWord;
    }

    public boolean getPlayer() {
        return player;
    }

    public void setAvailableMovesOnly(boolean _availableMovesOnly) {
        availableMovesOnly = _availableMovesOnly;
    }

    public boolean getAvailableMovesOnly() {
        return availableMovesOnly;
    }

    public CustList<SelectLineMove> getMoves() {
        return moves;
    }
}