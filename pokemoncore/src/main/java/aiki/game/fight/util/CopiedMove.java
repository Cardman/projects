package aiki.game.fight.util;
import code.util.StringList;
import code.util.ints.Displayable;

public final class CopiedMove implements Displayable {

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

    
    public static CopiedMove newCopiedMove(String _string) {
        return new CopiedMove(_string);
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

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(move);
        str_.append(SEPARATOR);
        str_.append(pp);
        return str_.toString();
    }
}
