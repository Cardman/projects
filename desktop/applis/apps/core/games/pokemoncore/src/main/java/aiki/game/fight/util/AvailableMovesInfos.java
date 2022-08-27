package aiki.game.fight.util;
import aiki.game.fight.KeyFightRound;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class AvailableMovesInfos {

    private KeyFightRound key;
    private final StringMap<BoolVal> moves;
    public AvailableMovesInfos(KeyFightRound _key,
            StringMap<BoolVal> _moves) {
        key = _key;
        moves = _moves;
    }
    public KeyFightRound getKey() {
        return key;
    }
    public void setFirst(KeyFightRound _key) {
        key = _key;
    }
    public StringMap<BoolVal> getMoves() {
        return moves;
    }

}
