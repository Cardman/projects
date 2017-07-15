package aiki.game;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.xml.FromAndToString;


@CheckedData
public final class UsesOfMove {

    private static final char SEPARATOR = ',';

    private static final String EMPTY_STRING = "";

    private short current;

    private short max;

    UsesOfMove() {
    }

    public UsesOfMove(String _string) {
        StringList elements_ = StringList.splitChars(_string, SEPARATOR);
        current = Short.parseShort(elements_.first());
        max = Short.parseShort(elements_.last());
    }

    public UsesOfMove(short _max) {
        current = _max;
        max = _max;
    }

    public UsesOfMove(short _current,short _max) {
        current = _current;
        max = _max;
    }

    @FromAndToString
    public static UsesOfMove newUsesOfMove(String _string) {
        return new UsesOfMove(_string);
    }

    @FromAndToString
    @Override
    public String toString() {
        return EMPTY_STRING+current+SEPARATOR+max;
    }

    public void heal(short _nb) {
        current += _nb;
    }
    public void fullHeal() {
        current = max;
    }

    public void useOne() {
        current--;
    }

    public void use(short _nb) {
        current-=_nb;
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
}
