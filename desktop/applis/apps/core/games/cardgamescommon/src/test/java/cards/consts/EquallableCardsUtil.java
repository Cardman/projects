package cards.consts;

import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableCardsUtil {

    private static final String DIFF = " != ";

    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringUtil.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

}
