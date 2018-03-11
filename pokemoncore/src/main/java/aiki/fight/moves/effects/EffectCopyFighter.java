package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.util.annot.RwXml;

@RwXml
public final class EffectCopyFighter extends Effect {

    private short ppForMoves;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (ppForMoves > 0) {
            return;
        }
        throw new DataException();
    }
    public short getPpForMoves() {
        return ppForMoves;
    }

    public void setPpForMoves(short _ppForMoves) {
        ppForMoves = _ppForMoves;
    }
}
