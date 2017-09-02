package code.serialize;
import code.serialize.classes.CompositeTwo;
import code.serialize.classes.MyEnum;
import code.serialize.classes.MyEnumTwo;
import code.util.StringList;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
            return;
        }
        assertError(_expected, _result);
    }

    public static void assertEq(CompositeTwo _expected, CompositeTwo _result) {
        if (checkNullity(_expected, _result)) {
            return;
        }
        if (_expected.eq(_result)) {
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
    public static void assertEq(MyEnum _expected, MyEnum _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
    }
    public static void assertEq(MyEnumTwo _expected, MyEnumTwo _result) {
        if (_expected == _result) {
            return;
        }
        assertError(_expected, _result);
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
