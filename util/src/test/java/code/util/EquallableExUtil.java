package code.util;
import code.util.classestest.KeyExample;
import code.util.classestest.Sex;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(KeyExample _expected, KeyExample _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(Sex _expected, Sex _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }
    private static boolean checkNullity(Object _expected, Object _result) {
        if (allNull(_expected, _result)) {
            return true;
        }
        if (onlyOneNull(_expected, _result)) {
            throw new AssertionError(null);
        }
        return false;
    }

    private static void assertError(Object _expected, Object _result) {
        throw new AssertionError(_expected+DIFF+_result);
    }

    private static boolean allNull(Object _expected, Object _result) {
        return _expected == null && _result == null;
    }

    private static boolean onlyOneNull(Object _expected, Object _result) {
        if (_expected == null) {
            return _result != null;
        }
        return _result == null;
    }
}
