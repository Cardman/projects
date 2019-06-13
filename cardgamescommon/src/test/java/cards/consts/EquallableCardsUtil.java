package cards.consts;

import code.util.Numbers;
import code.util.StringList;
import org.junit.Assert;

public final class EquallableCardsUtil {

    private static final String DIFF = " != ";

    private EquallableCardsUtil() {
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

}
