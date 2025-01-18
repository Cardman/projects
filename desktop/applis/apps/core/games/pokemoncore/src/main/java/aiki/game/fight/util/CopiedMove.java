package aiki.game.fight.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class CopiedMove implements Displayable {

    private static final char SEPARATOR = ',';

    private String move;

    private int pp;

    public CopiedMove(){
    }

    public CopiedMove(String _str) {
        StringList list_ = StringUtil.splitChars(_str, SEPARATOR);
        move = list_.first();
        pp = NumberUtil.parseInt(list_.last());
    }

    public CopiedMove(String _move, int _pp) {
        setMove(_move);
        setPp(_pp);
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

    public int getPp() {
        return pp;
    }

    public void setPp(int _pp) {
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
