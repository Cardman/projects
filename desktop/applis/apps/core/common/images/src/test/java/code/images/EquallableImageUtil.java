package code.images;

import org.junit.Assert;

import code.util.*;
import code.util.StringList;

public final class EquallableImageUtil {

    private static final String DIFF = " != ";

    private EquallableImageUtil() {
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }
}
