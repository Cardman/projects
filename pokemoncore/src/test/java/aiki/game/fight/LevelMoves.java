package aiki.game.fight;
import code.util.StringList;
import code.util.StringMap;

public final class LevelMoves {

    private short level;
    
    private StringMap<Short> movesPp;

    private StringList moves;
    
    public LevelMoves(short _level, StringList _moves) {
        level = _level;
        moves = _moves;
    }

    public LevelMoves(short _level, StringMap<Short> _moves) {
        level = _level;
        movesPp = _moves;
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

    public StringMap<Short> getMovesPp() {
        return movesPp;
    }
}
