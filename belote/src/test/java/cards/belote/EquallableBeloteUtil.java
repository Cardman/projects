package cards.belote;
import org.junit.Assert;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;

public final class EquallableBeloteUtil {

    private static final String DIFF = " != ";

    private EquallableBeloteUtil() {
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, sameValue(_expected, _result));
    }

    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
    public static void assertEq(HandBelote _expected, HandBelote _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(BidBeloteSuit _expected, BidBeloteSuit _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
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
