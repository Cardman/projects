package aiki.game;
import code.util.*;
import code.util.StringList;
import code.util.ints.Displayable;


public final class UsesOfMove implements Displayable{

    private static final char SEPARATOR = ',';

    private short current;

    private short max;

    public UsesOfMove(String _string) {
        StringList elements_ = StringList.splitChars(_string, SEPARATOR);
        current = (short) Numbers.parseInt(elements_.first());
        max = (short) Numbers.parseInt(elements_.last());
    }

    public UsesOfMove(short _max) {
        current = _max;
        max = _max;
    }

    public UsesOfMove(short _current,short _max) {
        current = _current;
        max = _max;
    }

    
    public static UsesOfMove newUsesOfMove(String _string) {
        return new UsesOfMove(_string);
    }

    public void heal(short _nb) {
        current += _nb;
    }
    public void fullHeal() {
        current = max;
    }

    public void boost(short _nb) {
        max += _nb;
    }

    public short getLostPp() {
        return (short) (max - current);
    }

    public short getCurrent() {
        return current;
    }

    public void setCurrent(short _current) {
        current = _current;
    }

    public short getMax() {
        return max;
    }

    public void setMax(short _max) {
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
