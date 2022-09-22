package code.images;

import org.junit.Assert;

public abstract class EquallableImageUtil {

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected,_result);
    }

    protected static void checkZero(int[] _array) {
        Assert.assertArrayEquals(_array, new int[_array.length]);
    }
}
