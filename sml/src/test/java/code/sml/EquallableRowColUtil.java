package code.sml;

import code.util.Numbers;
import code.util.StringList;
import org.junit.Assert;

public final class EquallableRowColUtil {

    private static final String DIFF = " != ";

    private EquallableRowColUtil() {
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
    public static void assertEq(RowCol _expected, RowCol _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }
}
