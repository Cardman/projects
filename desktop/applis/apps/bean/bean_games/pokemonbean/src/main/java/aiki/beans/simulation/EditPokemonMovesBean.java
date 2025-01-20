package aiki.beans.simulation;

import aiki.beans.WithFilterBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.moves.MovesBean;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.game.fight.FightSimulation;
import code.scripts.confs.PkScriptPages;
import code.util.*;

public class EditPokemonMovesBean extends WithFilterBean {
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private final StringMap<String> categories = new StringMap<String>();
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
        for (EntryCust<String, MoveData> k: getForms().getValMoveData(CST_MOVES_EDIT_SET).entryList()) {
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
            moves.add(MovesBean.buildLine(translationsMoves_,translationsTypes_,translationsCategories_,k.getKey(),moveData_,getDataBase()));
        }
        moves.sortElts(new ComparatorMoves());
    }
    public String cancel() {
        getForms().putMoves(CST_MOVES_EDIT_SET, new StringMap<MoveData>());
        return redirect();
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
        return redirect();
    }
    public String search() {
        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        AbsMap<String,MoveData> moves_;
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        moves_ = new StringList();
        StringMap<MoveData> set_ = new StringMap<MoveData>();
        if (player) {
            if (availableMovesOnly) {
                String namePk_ = getForms().getValStr(CST_POKEMON_NAME_EDIT);
                long level_ = getForms().getValLong(CST_POKEMON_LEVEL_EDIT);
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
        getForms().putMoves(CST_MOVES_EDIT_SET, moves_);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML;
    }

    private String redirect() {
        if (player) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML;
        }
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public StringMap<String> getCategories() {
        return categories;
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