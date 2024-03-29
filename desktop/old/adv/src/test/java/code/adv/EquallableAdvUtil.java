package code.adv;

import code.util.StringList;
import org.junit.Assert;

public abstract class EquallableAdvUtil {

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
        int expSize_ = _expected.size();
        Assert.assertEquals(expSize_,_result.size());
        for (int i = 0; i < expSize_; i++) {
            Assert.assertEquals(_expected.get(i),_result.get(i));
        }
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
