package code.util;

import code.util.classestest.KeyExample;
import code.util.classestest.MyCmp;
import org.junit.Assert;

public abstract class EquallableExUtil {

    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Object _value) {
        Assert.assertNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertSame(Object _expected, Object _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, short _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, byte _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, char _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(KeyExample _expected, KeyExample _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }

    public static void assertEq(MyCmp _expected, MyCmp _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }
}
