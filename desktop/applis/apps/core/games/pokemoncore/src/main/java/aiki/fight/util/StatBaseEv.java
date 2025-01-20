package aiki.fight.util;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class StatBaseEv implements Displayable{

    private static final char SEPARATOR = ';';

    private final long base;

    private final long ev;

    public StatBaseEv(String _str) {
        StringList elements_ = StringUtil.splitChars(_str, SEPARATOR);
        base = NumberUtil.parseLongZero(elements_.first());
        ev = NumberUtil.parseLongZero(elements_.last());
    }

    public StatBaseEv(long _base, long _ev) {
        base = _base;
        ev = _ev;
    }

    
    public static StatBaseEv newStatBaseEv(String _string) {
        return new StatBaseEv(_string);
    }

    public long getBase() {
        return base;
    }

    public long getEv() {
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
