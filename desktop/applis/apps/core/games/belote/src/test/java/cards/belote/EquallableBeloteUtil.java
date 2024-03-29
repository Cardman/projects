package cards.belote;
import cards.belote.enumerations.*;
import cards.consts.EndGameState;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import org.junit.Assert;

import cards.consts.Suit;


public abstract class EquallableBeloteUtil {

    protected static void bid(GameBelote _game, BidBeloteSuit _bid) {
        _game.ajouterContrat(_bid);
    }

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(String _mess,boolean _value) {
        Assert.assertFalse(_mess,_value);
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

    public static void assertEq(DealingBelote _expected, DealingBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(DeclaresBelote _expected, DeclaresBelote _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BeloteTrumpPartner _expected, BeloteTrumpPartner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected,_result);
    }

    protected static HandBelote create(CardBelote... _cards) {
        return HandBelote.create(_cards);
//        HandBelote h_ = new HandBelote();
//        for (CardBelote c : _cards) {
//            h_.ajouter(c);
//        }
//        return h_;
    }
}
