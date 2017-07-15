package aiki.game.fight.util;
import code.util.PairNumber;
import code.util.StringMap;

public final class AvailableMovesInfos {

    private PairNumber<Byte,Byte> key;
    private final StringMap<Boolean> moves;
    public AvailableMovesInfos(PairNumber<Byte, Byte> _key,
            StringMap<Boolean> _moves) {
        key = _key;
        moves = _moves;
    }
    public PairNumber<Byte, Byte> getKey() {
        return key;
    }
    public void setFirst(PairNumber<Byte, Byte> _key) {
        key = _key;
    }
    public StringMap<Boolean> getMoves() {
        return moves;
    }

}
