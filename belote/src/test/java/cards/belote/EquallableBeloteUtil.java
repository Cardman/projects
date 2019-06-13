package cards.belote;
import org.junit.Assert;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import code.util.Numbers;
import code.util.StringList;

public final class EquallableBeloteUtil {

    private static final String DIFF = " != ";

    private EquallableBeloteUtil() {
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }
    public static void assertEq(HandBelote _expected, HandBelote _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }

    public static void assertEq(BidBeloteSuit _expected, BidBeloteSuit _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }
    
    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    
    public static void assertEq(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    
    public static void assertEq(BidBelote _expected, BidBelote _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        Assert.assertSame(_expected, _result);
    }
}
