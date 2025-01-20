package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.util.DataInfoChecker;


public abstract class EffectEndRound extends Effect {

    private String failEndRound;
    private long endRoundRank;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositive(endRoundRank,_data);
    }

    public String getFailEndRound() {
        return failEndRound;
    }

    public void setFailEndRound(String _failEndRound) {
        failEndRound = _failEndRound;
    }

    public long getEndRoundRank() {
        return endRoundRank;
    }

    public void setEndRoundRank(long _endRoundRank) {
        endRoundRank = _endRoundRank;
    }

    public abstract RelationType getRelation();
}
