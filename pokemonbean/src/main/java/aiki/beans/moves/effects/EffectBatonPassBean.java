package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import code.util.StringList;
import code.util.StringMap;

public class EffectBatonPassBean extends EffectBean {
    private StringList moves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        moves_ = new StringList();
        moves_.addAllElts(data_.getMovesEffectIndiv());
        moves_.addAllElts(data_.getMovesEffectProt());
        moves_.addAllElts(data_.getMovesEffEndRoundIndiv());
        moves_.addAllElts(data_.getMovesEffectUnprot());
        moves_.addAllElts(data_.getTrappingMoves());
        moves_.removeDuplicates();
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        moves = moves_;
    }
    public String getTrMove(Long _index) {
        String move_ = moves.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMove(Long _index) {
        String move_ = moves.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    public StringList getMoves() {
        return moves;
    }
}