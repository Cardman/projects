package code.expressionlanguage.common;

public final class StringExpUtil {
    private StringExpUtil() {
    }

    public static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i >= _len) {
            return false;
        }
        return _str.charAt(_i) == _value;
    }
}
