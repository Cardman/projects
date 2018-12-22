package code.util;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;

import code.util.classestest.KeyExample;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Boolean.toString(_expected),DIFF,Boolean.toString(_result)), _expected == _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(long _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(BigInteger _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), _expected.equals(_result));
    }

    public static void assertEq(BigDecimal _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), _expected.equals(_result));
    }
    private static boolean sameValue(long _expected, Number _result) {
        return _expected == _result.longValue();
    }

    public static void assertEq(KeyExample _expected, KeyExample _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
}
