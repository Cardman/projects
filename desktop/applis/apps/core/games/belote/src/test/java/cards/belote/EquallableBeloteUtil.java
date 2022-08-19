package cards.belote;
import org.junit.Assert;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import code.util.*;


public abstract class EquallableBeloteUtil {

    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Object _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(Object _expected, Object _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Object _expected, Object _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(HandBelote _expected, HandBelote _result) {
        Assert.assertNotNull(_result);
        int size_ = _expected.total();
        Assert.assertEquals(size_, _result.total());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.carte(i),_result.carte(i));
        }
    }

    public static void assertEq(BidBeloteSuit _expected, BidBeloteSuit _result) {
        Assert.assertNotNull(_result);
        Assert.assertSame(_expected.getSuit(), _result.getSuit());
        Assert.assertSame(_expected.getBid(), _result.getBid());
        Assert.assertEquals(_expected.getPoints(),_result.getPoints());
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
    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEqSet(CustList<BidBeloteSuit> _bids, CustList<BidBeloteSuit> _expected) {
        Assert.assertTrue(EquallableBeloteUtil.equalsSet(_expected,_bids));
    }

    private static boolean equalsSet(CustList<BidBeloteSuit> _list1, CustList<BidBeloteSuit> _list2) {
        for (BidBeloteSuit a: _list2) {
            boolean contains_ = false;
            for (BidBeloteSuit b: _list1) {
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (BidBeloteSuit a: _list1) {
            boolean contains_ = false;
            for (BidBeloteSuit b: _list2) {
                if (a.eq(b)) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
}
