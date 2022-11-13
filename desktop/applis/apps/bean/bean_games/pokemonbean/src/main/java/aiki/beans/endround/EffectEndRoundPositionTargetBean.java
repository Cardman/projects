package aiki.beans.endround;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
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
        movesSameCategory = moves();
    }

    private StringList moves() {
        DataBase data_ = getDataBase();
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
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }

    public StringList getMovesSameCategory() {
        return movesSameCategory;
    }

    public String getTrTargetRelationMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_;
        translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSameCategory.get(_index));
    }

    public String clickTargetRelationMove(int _index) {
        StringList moves_ = moves();
        return tryRedirectMv(moves_.get(_index));
    }
}