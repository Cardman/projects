package aiki.beans.endround;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import code.util.StringList;
import code.util.StringMap;

public class EffectEndRoundPositionTargetBean extends EffectEndRoundBean {

    private StringList movesSameCategory;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectEndRoundPositionTargetRelation) {
                    moves_.add(m);
                }
            }
        }
        moves_.removeDuplicates();
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesSameCategory = moves_;
    }

    public StringList getMovesSameCategory() {
        return movesSameCategory;
    }

    public String getTrMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSameCategory.get(_index.intValue()));
    }

    public String clickMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        moves_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectEndRoundPositionTargetRelation) {
                    moves_.add(m);
                }
            }
        }
        moves_.removeDuplicates();
        moves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        getForms().put(MOVE, moves_.get(_index.intValue()));
        return MOVE;
    }
}
