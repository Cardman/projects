package aiki.game.fight.util;
import code.serialize.CheckedData;
import code.util.StringList;
import code.xml.FromAndToString;

@CheckedData
public final class CopiedMove {

    private static final char SEPARATOR = ',';

    private String move;

    private short pp;

    public CopiedMove(){
    }

    public CopiedMove(String _str) {
        StringList list_ = StringList.splitChars(_str, SEPARATOR);
        move = list_.first();
        pp = Short.parseShort(list_.last());
    }

    public CopiedMove(String _move, short _pp) {
        move = _move;
        pp = _pp;
    }

    @FromAndToString
    public static CopiedMove newCopiedMove(String _string) {
        return new CopiedMove(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return move+SEPARATOR+pp;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }

    public short getPp() {
        return pp;
    }

    public void setPp(short _pp) {
        pp = _pp;
    }
}
