package code.util;
import java.math.BigDecimal;
import java.math.BigInteger;

import code.util.classestest.MyCmp;
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
        Assert.assertTrue(StringList.concat(Boolean.toString(_expected),DIFF,Boolean.toString(_result)), _expected == _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, short _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, byte _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    public static void assertEq(long _expected, char _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Character.toString(_result)), _expected == _result);
    }
    public static void assertEq(BigInteger _expected, BigInteger _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), _expected.equals(_result));
    }

    public static void assertEq(BigDecimal _expected, BigDecimal _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), _expected.equals(_result));
    }
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    public static void assertEq(KeyExample _expected, KeyExample _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(MyCmp _expected, MyCmp _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
}
