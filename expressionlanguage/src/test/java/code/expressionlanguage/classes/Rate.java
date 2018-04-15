package code.expressionlanguage.classes;
import code.util.StringList;

public final class Rate {

    private static final String ZERO = "0";

    private final boolean zero;

    public Rate(String _value) {
        zero = StringList.quickEq(_value,ZERO);
    }

    public static Rate newRate(String _value) {
        return new Rate(_value);
    }

    public boolean isZero() {
        return zero;
    }
}
