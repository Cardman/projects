package aiki.fight.util;
import code.serialize.CheckedData;
import code.sml.FromAndToString;
import code.util.StringList;

@CheckedData
public final class StatBaseEv {

    private static final char SEPARATOR = ';';

    private static final String EMPTY_STRING = "";

    private final short base;

    private final short ev;

    public StatBaseEv(String _str) {
        StringList elements_ = StringList.splitChars(_str, SEPARATOR);
        base = Short.parseShort(elements_.first());
        ev = Short.parseShort(elements_.last());
    }

    public StatBaseEv(short _base, short _ev) {
        base = _base;
        ev = _ev;
    }

    @FromAndToString
    public static StatBaseEv newStatBaseEv(String _string) {
        return new StatBaseEv(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return EMPTY_STRING+base+SEPARATOR+ev;
    }

    public short getBase() {
        return base;
    }

    public short getEv() {
        return ev;
    }
}
