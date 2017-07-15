package aiki.beans.endround;
import code.bean.Accessible;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;

public class EffectEndRoundPositionTargetBean extends EffectEndRoundBean {

    @Accessible
    private StringList moves;

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
        moves = moves_;
    }

    @Accessible
    private String getTrMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(moves.get(_index.intValue()));
    }

    @Accessible
    private String clickMove(Long _index) {
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
