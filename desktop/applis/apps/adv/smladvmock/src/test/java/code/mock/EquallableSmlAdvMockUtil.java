package code.mock;

import org.junit.Assert;

public abstract class EquallableSmlAdvMockUtil {
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

}
