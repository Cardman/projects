package cards.tarot;
import org.junit.Assert;

import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.Numbers;
import code.util.StringList;

public final class EquallableTarotUtil {

    private static final String DIFF = " != ";

    private EquallableTarotUtil() {
    }
    public static void assertEq(long _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }

    private static boolean sameValue(long _expected, Number _result) {
        return _expected == _result.longValue();
    }
    public static void assertEq(HandTarot _expected, HandTarot _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }
    
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    
    public static void assertEq(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
}
