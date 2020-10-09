package aiki.fight.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class LevelMove implements Displayable {

    private static final char SEPARATOR = ';';

    private short level;

    private String move;

    public LevelMove(){
    }

    public LevelMove(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        level = (short) NumberUtil.parseInt(elements_.first());
        move = elements_.last();
    }

    public LevelMove(short _level, String _move) {
        level = _level;
        move = _move;
    }

    
    public static LevelMove newLevelMove(String _string) {
        return new LevelMove(_string);
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(level);
        str_.append(SEPARATOR);
        str_.append(move);
        return str_.toString();
    }
}
