package code.images;

import org.junit.Assert;

import code.util.*;
import code.util.StringList;

public final class EquallableImageUtil {

    private EquallableImageUtil() {
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected,_result);
    }

}
