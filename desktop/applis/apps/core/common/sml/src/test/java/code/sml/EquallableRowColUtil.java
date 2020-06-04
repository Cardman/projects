package code.sml;

import org.junit.Assert;

public final class EquallableRowColUtil {

    private EquallableRowColUtil() {
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
    public static void assertEq(RowCol _expected, RowCol _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.display(),_expected.getRow(),_result.getRow());
        Assert.assertEquals(_expected.display(),_expected.getCol(),_result.getCol());
    }

}
