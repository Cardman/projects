package aiki.fight.util;
import code.datacheck.CheckedData;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

@CheckedData
public final class LevelMove implements Equallable<LevelMove> {

    private static final char SEPARATOR = ';';

    private static final String EMPTY_STRING = "";

    private short level;

    private String move;

    public LevelMove(){
    }

    public LevelMove(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        level = Short.parseShort(elements_.first());
        move = elements_.last();
    }

    public LevelMove(short _level, String _move) {
        level = _level;
        move = _move;
    }

    @FromAndToString
    public static LevelMove newLevelMove(String _string) {
        return new LevelMove(_string);
    }

    @Override
    public boolean eq(LevelMove _obj) {
        if (!Numbers.eq(level, _obj.level)) {
            return false;
        }
        if (!StringList.quickEq(move, _obj.move)) {
            return false;
        }
        return true;
    }

    @FromAndToString
    @Override
    public String toString() {
        return EMPTY_STRING+level+SEPARATOR+move;
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
}
