package aiki.game;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;


public final class UsesOfMove implements Displayable{

    private static final char SEPARATOR = ',';

    private long current;

    private long max;

    public UsesOfMove(String _string) {
        StringList elements_ = StringUtil.splitChars(_string, SEPARATOR);
        current =NumberUtil.parseLongZero(elements_.first());
        max = NumberUtil.parseLongZero(elements_.last());
    }

    public UsesOfMove(long _max) {
        current = _max;
        max = _max;
    }

    public UsesOfMove(long _current,long _max) {
        current = _current;
        max = _max;
    }

    
    public static UsesOfMove newUsesOfMove(String _string) {
        return new UsesOfMove(_string);
    }

    public void heal(long _nb) {
        current += _nb;
    }
    public void fullHeal() {
        current = max;
    }

    public void boost(long _nb) {
        max += _nb;
    }

    public long getLostPp() {
        return max - current;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long _current) {
        current = _current;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long _max) {
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
