package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.effects.enums.RelationType;


public abstract class EffectEndRound extends Effect {

    private String failEndRound;
    private int endRoundRank;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (endRoundRank <= 0) {
            _data.setError(true);
            return;

        }
    }

    public String getFailEndRound() {
        return failEndRound;
    }

    public void setFailEndRound(String _failEndRound) {
        failEndRound = _failEndRound;
    }

    public int getEndRoundRank() {
        return endRoundRank;
    }

    public void setEndRoundRank(int _endRoundRank) {
        endRoundRank = _endRoundRank;
    }

    public abstract RelationType getRelation();
}
