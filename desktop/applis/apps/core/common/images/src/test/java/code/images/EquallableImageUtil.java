package code.images;

import org.junit.Assert;

public abstract class EquallableImageUtil {

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
    public static void assertNotSame(Object _expected, Object _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected,_result);
    }

}
