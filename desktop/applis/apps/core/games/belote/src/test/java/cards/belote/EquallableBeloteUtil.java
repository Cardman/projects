package cards.belote;
import cards.consts.EndGameState;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import org.junit.Assert;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;


public abstract class EquallableBeloteUtil {

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
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
    public static void assertSame(EndGameState _expected, EndGameState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(CardBelote _expected, CardBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Role _expected, Role _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BidBeloteSuit _expected, BidBeloteSuit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(RulesBelote _expected, RulesBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GameBelote _expected, GameBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BidBelote _expected, BidBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(PossibleTrickWinner _expected, PossibleTrickWinner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Suit _expected, Suit _result) {
        Assert.assertNotSame(_expected, _result);
    }
    public static void assertNotSame(RulesBelote _expected, RulesBelote _result) {
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
        Assert.assertEquals(_expected.getCards().getList(),_result.getCards().getList());
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

}
