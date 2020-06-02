package aiki.game.fight.util;
import aiki.game.fight.KeyFightRound;
import code.util.StringMap;

public final class AvailableMovesInfos {

    private KeyFightRound key;
    private final StringMap<Boolean> moves;
    public AvailableMovesInfos(KeyFightRound _key,
            StringMap<Boolean> _moves) {
        key = _key;
        moves = _moves;
    }
    public KeyFightRound getKey() {
        return key;
    }
    public void setFirst(KeyFightRound _key) {
        key = _key;
    }
    public StringMap<Boolean> getMoves() {
        return moves;
    }

}
