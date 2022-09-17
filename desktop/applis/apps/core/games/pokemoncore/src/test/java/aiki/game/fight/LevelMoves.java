package aiki.game.fight;

import code.util.StringList;

public final class LevelMoves {

    private short level;

    private StringList moves;
    public LevelMoves(short _level, StringList _moves) {
        level = _level;
        moves = _moves;
    }
    public short getFirst() {
        return level;
    }
    public StringList getSecond() {
        return moves;
    }

    public short getLevel() {
        return level;
    }

    public StringList getMoves() {
        return moves;
    }

}
