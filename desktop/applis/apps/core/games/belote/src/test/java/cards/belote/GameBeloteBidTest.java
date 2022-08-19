package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.Suit;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteBidTest extends EquallableBeloteUtil {

    @Test
    public void contrat1Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.HEART_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
    }

    @Test
    public void contrat2Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }

    @Test
    public void contrat3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.HEART_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
    }

    @Test
    public void contrat4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }

    @Test
    public void contrat5Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.OTHER_SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
    }

    @Test
    public void contrat6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.NO_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }

    @Test
    public void contrat7Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_7);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_8);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }

    @Test
    public void contrat8Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_7);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_8);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }

    @Test
    public void contrat9Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.HEART_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
    }

    @Test
    public void contrat10Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.OTHER_SUIT, contrat_.getBid());
        assertSame(Suit.SPADE, contrat_.getSuit());
    }

    @Test
    public void contrat11Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.CLUB_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.SPADE_9);
        h_.ajouter(CardBelote.CLUB_9);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.OTHER_SUIT, contrat_.getBid());
        assertSame(Suit.CLUB, contrat_.getSuit());
    }

    @Test
    public void contrat12Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.CLUB_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_9);
        h_.ajouter(CardBelote.CLUB_9);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.OTHER_SUIT, contrat_.getBid());
        assertSame(Suit.SPADE, contrat_.getSuit());
    }

    @Test
    public void contrat13Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.SPADE_KING);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.SPADE_9);
        h_.ajouter(CardBelote.SPADE_QUEEN);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.DIAMOND_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,true,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.OTHER_SUIT, contrat_.getBid());
        assertSame(Suit.SPADE, contrat_.getSuit());
    }

    @Test
    public void contrat14Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
        assertEq(110,contrat_.getPoints());
    }

    @Test
    public void contrat15Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.NO_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(120,contrat_.getPoints());
    }

    @Test
    public void contrat16Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_8);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(0,contrat_.getPoints());
    }

    @Test
    public void contrat17Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.DIAMOND_7);
        h_.ajouter(CardBelote.DIAMOND_8);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(0,contrat_.getPoints());
    }

    @Test
    public void contrat18Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setPoints(120);
        bid_.setBid(BidBelote.ALL_TRUMP);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(0,contrat_.getPoints());
    }

    @Test
    public void contrat19Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(110);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(0,contrat_.getPoints());
    }

    @Test
    public void contrat20Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.ALL_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(162,contrat_.getPoints());
    }

    @Test
    public void contrat21Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.NO_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(162,contrat_.getPoints());
    }

    @Test
    public void contrat22Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.contrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.DIAMOND, contrat_.getSuit());
        assertEq(162,contrat_.getPoints());
    }

    @Test
    public void strategieContrat1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setPoints(160);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.strategieContrat();
        assertSame(BidBelote.NO_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(162,contrat_.getPoints());
    }
    @Test
    public void strategieContrat2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.COINCHE_2_VS_2);
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.HEART_QUEEN);
        h_.ajouter(CardBelote.HEART_1);
        HandBelote l_ = new HandBelote();
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.strategieContrat();
        assertSame(BidBelote.NO_TRUMP, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
        assertEq(162,contrat_.getPoints());
    }

    @Test
    public void strategieContrat3Test() {
        RulesBelote r_ = new RulesBelote();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.HEART_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.strategieContrat();
        assertSame(BidBelote.SUIT, contrat_.getBid());
        assertSame(Suit.HEART, contrat_.getSuit());
    }

    @Test
    public void strategieContrat4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote l_ = new HandBelote();
        l_.ajouter(CardBelote.HEART_9);
        GameBeloteBid g_ = new GameBeloteBid(h_,r_, bid_,false,l_);
        BidBeloteSuit contrat_ = g_.strategieContrat();
        assertSame(BidBelote.FOLD, contrat_.getBid());
        assertSame(Suit.UNDEFINED, contrat_.getSuit());
    }
}
