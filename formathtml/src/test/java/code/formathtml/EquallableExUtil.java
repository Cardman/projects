package code.formathtml;

import code.formathtml.classes.EnumNumber;

public class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(EnumNumber _expected, EnumNumber _result) {
        if (_expected == _result) {
            return;
        }
        if (onlyOneNull(_expected, _result)) {
            throw new AssertionError(null);
        }
        assertError(_expected, _result);
    }

    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }

    private static void assertError(Object _expected, Object _result) {
        throw new AssertionError(_expected+DIFF+_result);
    }
}
