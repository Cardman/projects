package code.formathtml;

import org.junit.Assert;

import code.formathtml.classes.EnumNumber;
import code.formathtml.classes.Rate;
import code.util.StringList;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, StringList.quickEq(_expected, _result));
    }

    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, sameValue(_expected, _result));
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(EnumNumber _expected, EnumNumber _result) {
        Assert.assertSame(_expected, _result);
    }
    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
}
