package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.EnumList;
import code.util.EnumMap;
import org.junit.Test;

import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class GameTarotCallDiscardTest {
    @Test
    public void cartesNonMaitressesDebut1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot called_ = new HandTarot();
        EnumMap<Suit, HandTarot> seq_ = handSuit_.couleurs();
        EnumMap<Suit, HandTarot> lead_ = GameTarotCommon.cartesMaitresses(seq_, called_.couleurs());
        HandTarot h_ = GameTarotCallDiscard.cartesNonMaitressesDebut(handSuit_,lead_, new HandTarot(), new HandTarot());
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardTarot.HEART_1));
        assertTrue(h_.contient(CardTarot.SPADE_QUEEN));
        assertTrue(h_.contient(CardTarot.SPADE_KNIGHT));
        assertTrue(h_.contient(CardTarot.SPADE_JACK));
        assertTrue(h_.contient(CardTarot.SPADE_10));
        assertTrue(h_.contient(CardTarot.SPADE_9));
        assertTrue(h_.contient(CardTarot.SPADE_8));
        assertTrue(h_.contient(CardTarot.SPADE_1));
    }
    @Test
    public void cartesNonMaitressesDebut2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot called_ = new HandTarot();
        called_.ajouter(CardTarot.SPADE_KING);
        EnumMap<Suit, HandTarot> seq_ = handSuit_.couleurs();
        EnumMap<Suit, HandTarot> lead_ = GameTarotCommon.cartesMaitresses(seq_, called_.couleurs());
        HandTarot pseudo_ = GameTarotBid.cartesPseudoMaitresses(seq_, called_, new HandTarot().couleurs()).getVal(Suit.SPADE);
        HandTarot h_ = GameTarotCallDiscard.cartesNonMaitressesDebut(handSuit_,lead_,called_,pseudo_);
        assertEq(2, h_.total());
        assertTrue(h_.contient(CardTarot.HEART_1));
        assertTrue(h_.contient(CardTarot.SPADE_1));
    }
    @Test
    public void couleurAappeler1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.SPADE_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        handSuit_.ajouter(CardTarot.CLUB_9);
        handSuit_.ajouter(CardTarot.CLUB_8);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        handSuit_.ajouter(CardTarot.CLUB_9);
        handSuit_.ajouter(CardTarot.CLUB_8);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        handSuit_.ajouter(CardTarot.CLUB_9);
        handSuit_.ajouter(CardTarot.CLUB_8);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.HEART_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        handSuit_.ajouter(CardTarot.CLUB_9);
        handSuit_.ajouter(CardTarot.CLUB_8);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        handSuit_.ajouter(CardTarot.CLUB_9);
        handSuit_.ajouter(CardTarot.CLUB_8);
        handSuit_.ajouter(CardTarot.CLUB_7);
        handSuit_.ajouter(CardTarot.CLUB_3);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler7Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_10);
        handSuit_.ajouter(CardTarot.DIAMOND_9);
        handSuit_.ajouter(CardTarot.DIAMOND_8);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler8Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_10);
        handSuit_.ajouter(CardTarot.DIAMOND_9);
        handSuit_.ajouter(CardTarot.DIAMOND_8);
        handSuit_.ajouter(CardTarot.DIAMOND_5);
        handSuit_.ajouter(CardTarot.DIAMOND_3);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler9Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler10Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_10);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler11Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_10);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler12Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_10);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler13Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler14Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler15Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.DIAMOND_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler16Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.CLUB_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler17Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.HEART_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler18Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.HEART_KING,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler19Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.TRUMP_21,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler20Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.TRUMP_1,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler21Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_1);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.EXCUSE,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler22Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.EXCUSE);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.TRUMP_1,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler23Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.TRUMP_21,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler24Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_1);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        handSuit_.ajouter(CardTarot.CLUB_10);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_CHAR);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.EXCUSE,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void couleurAappeler25Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_1);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        HandTarot callable_ = g_.callableCards();
        assertSame(CardTarot.TRUMP_21,GameTarotCallDiscard.couleurAappeler(callable_,handSuit_));
    }
    @Test
    public void strategieAppel1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.CLUB_KING,h_.premiereCarte());
    }
    @Test
    public void strategieAppel2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.CLUB_KING,h_.premiereCarte());
    }
    @Test
    public void strategieAppel3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.SPADE_KING,h_.premiereCarte());
    }
    @Test
    public void strategieAppel4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.SPADE_KING,h_.premiereCarte());
    }
    @Test
    public void strategieAppel5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.SPADE_KING,h_.premiereCarte());
    }
    @Test
    public void strategieAppel6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_5);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = newGameTarotBid(handSuit_,r_,BidTarot.TAKE);
        GameTarotCallDiscard d_ = new GameTarotCallDiscard(g_,6);
        HandTarot h_ = d_.strategieAppel();
        assertEq(1, h_.total());
        assertSame(CardTarot.SPADE_KING,h_.premiereCarte());
    }
    static GameTarotBid newGameTarotBid(HandTarot _h, RulesTarot _r, BidTarot _b) {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(_b);
        int nb_ = _r.getRepartition().getNombreJoueurs();
        for (int i = 1; i < nb_; i++) {
            bids_.add(BidTarot.FOLD);
        }
        return new GameTarotBid(_h,_r, bids_,_b);
    }
}
