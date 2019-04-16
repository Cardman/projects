package aiki.fight.util;
import code.util.Numbers;
import code.util.StringList;
import code.util.ints.Displayable;

public final class StatBaseEv implements Displayable{

    private static final char SEPARATOR = ';';

    private final short base;

    private final short ev;

    public StatBaseEv(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        base = (short) Numbers.parseInt(elements_.first());
        ev = (short) Numbers.parseInt(elements_.last());
    }

    public StatBaseEv(short _base, short _ev) {
        base = _base;
        ev = _ev;
    }

    
    public static StatBaseEv newStatBaseEv(String _string) {
        return new StatBaseEv(_string);
    }

    public short getBase() {
        return base;
    }

    public short getEv() {
        return ev;
    }

    
    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(base);
        str_.append(SEPARATOR);
        str_.append(ev);
        return str_.toString();
    }
}
