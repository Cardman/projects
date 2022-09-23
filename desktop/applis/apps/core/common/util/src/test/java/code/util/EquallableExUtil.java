package code.util;

import code.util.classestest.MyEnum;
import code.util.core.BoolVal;
import code.util.ints.Comparing;
import org.junit.Assert;

public abstract class EquallableExUtil {

    public static void assertNotNull(Comparing<String> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNull(String _value) {
        Assert.assertNull(_value);
    }

    public static void assertNull(Integer _value) {
        Assert.assertNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertSame(Ints _expected, Ints _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MyEnum _expected, MyEnum _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getList(),_result.getList());
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

}
