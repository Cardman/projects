package aiki.game;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class UsesOfMove implements Displayable{

    private static final char SEPARATOR = ',';

    private int current;

    private int max;

    public UsesOfMove(String _string) {
        StringList elements_ = StringUtil.splitChars(_string, SEPARATOR);
        current =NumberUtil.parseInt(elements_.first());
        max = NumberUtil.parseInt(elements_.last());
    }

    public UsesOfMove(int _max) {
        current = _max;
        max = _max;
    }

    public UsesOfMove(int _current,int _max) {
        current = _current;
        max = _max;
    }

    
    public static UsesOfMove newUsesOfMove(String _string) {
        return new UsesOfMove(_string);
    }

    public void heal(int _nb) {
        current += _nb;
    }
    public void fullHeal() {
        current = max;
    }

    public void boost(int _nb) {
        max += _nb;
    }

    public int getLostPp() {
        return max - current;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int _current) {
        current = _current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int _max) {
        max = _max;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(current);
        str_.append(SEPARATOR);
        str_.append(max);
        return str_.toString();
    }
}
