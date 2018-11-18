package cards.tarot;
import org.junit.Assert;

import code.util.Numbers;
import code.util.StringList;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;

public final class EquallableTarotUtil {

    private static final String DIFF = " != ";

    private EquallableTarotUtil() {
    }
    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }

    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
    public static void assertEq(HandTarot _expected, HandTarot _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
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
