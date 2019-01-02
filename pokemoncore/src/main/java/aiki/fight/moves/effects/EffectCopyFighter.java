package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;


public final class EffectCopyFighter extends Effect {

    private short ppForMoves;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        if (ppForMoves > 0) {
            return;
        }
        _data.setError(true);
        return;

    }

    public short getPpForMoves() {
        return ppForMoves;
    }

    public void setPpForMoves(short _ppForMoves) {
        ppForMoves = _ppForMoves;
    }
}
