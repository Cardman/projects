package code.serialize;
import org.junit.Assert;

import code.serialize.classes.CompositeTwo;
import code.serialize.classes.MyEnum;
import code.serialize.classes.MyEnumTwo;
import code.util.StringList;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(boolean _expected, Object _result) {
        Assert.assertNotNull(_result);
        Assert.assertSame(Boolean.class,_result.getClass());
        Assert.assertTrue(_expected+DIFF+_result, _expected == ((Boolean)_result).booleanValue());
    }

    public static void assertEq(String _expected, Object _result) {
        Assert.assertNotNull(_result);
        Assert.assertSame(String.class,_result.getClass());
        Assert.assertTrue(_expected+DIFF+_result, StringList.quickEq(_expected, (String)_result));
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, sameValue(_expected, _result));
    }

    public static void assertEq(CompositeTwo _expected, CompositeTwo _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(Character _expected, Character _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.charValue() == _result.charValue());
    }

    public static void assertEq(MyEnum _expected, MyEnum _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(MyEnumTwo _expected, MyEnumTwo _result) {
        Assert.assertSame(_expected, _result);
    }
    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }

}
