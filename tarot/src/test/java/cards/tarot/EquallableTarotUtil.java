package cards.tarot;
import code.maths.Rate;
import code.util.CustList;
import org.junit.Assert;

import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.StringList;

public final class EquallableTarotUtil {

    private static final String DIFF = " != ";

    private EquallableTarotUtil() {
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
    public static void assertEq(HandTarot _expected, HandTarot _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(eq(_expected, _result));
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertTrue(StringList.quickEq(_expected,_result));
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

    public static boolean eq(HandTarot _current, HandTarot _o) {
        if(_o.total()!= _current.total()) {
            return false;
        }
        boolean id_=true;
        int nbCards_ = _current.total();
        for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
            if (!CardTarot.eq(_o.carte(i), _current.carte(i))) {
                id_ = false;
            }
        }
        return id_;
    }
}
