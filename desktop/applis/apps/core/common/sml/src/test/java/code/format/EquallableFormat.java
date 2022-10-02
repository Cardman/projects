package code.format;

import org.junit.Assert;

public abstract class EquallableFormat {

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
}
