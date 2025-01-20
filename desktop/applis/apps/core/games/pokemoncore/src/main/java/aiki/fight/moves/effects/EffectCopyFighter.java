package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;


public final class EffectCopyFighter extends Effect {

    private long ppForMoves;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkPositive(ppForMoves,_data);

    }

    public long getPpForMoves() {
        return ppForMoves;
    }

    public void setPpForMoves(long _ppForMoves) {
        ppForMoves = _ppForMoves;
    }
}
