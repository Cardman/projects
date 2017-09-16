package code.util;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;

import code.util.classestest.KeyExample;
import code.util.classestest.Sex;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected == _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, StringList.quickEq(_expected, _result));
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, sameValue(_expected, _result));
    }

    public static void assertEq(BigInteger _expected, BigInteger _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, BigIntegers.eq(_expected, _result));
    }

    public static void assertEq(BigDecimal _expected, BigDecimal _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, GenericNumbers.eq(_expected, _result));
    }

    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }

    public static void assertEq(KeyExample _expected, KeyExample _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }
    public static void assertEq(Sex _expected, Sex _result) {
        Assert.assertSame(_expected, _result);
    }
}
