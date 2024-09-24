package cards.solitaire;

import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DealSpiderTest extends EquallableSolitaireUtil {
    @Test
    public void deal() {
        DealSpider d_ = new DealSpider();
        assertEq(SolitaireType.SPIDER,d_.type());
        d_.setActions(new CustList<ActionSolitaire>());
        d_.setHands(new CustList<HandSolitaire>());
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.deal(DefaultGenerator.oneElt());
        assertTrue(d_.valid(mes()));
        assertEq(12,d_.getHands().size());
        assertEq(50,d_.getHands().get(0).total());
        assertEq(6,d_.getHands().get(1).total());
        assertEq(6,d_.getHands().get(2).total());
        assertEq(6,d_.getHands().get(3).total());
        assertEq(6,d_.getHands().get(4).total());
        assertEq(5,d_.getHands().get(5).total());
        assertEq(5,d_.getHands().get(6).total());
        assertEq(5,d_.getHands().get(7).total());
        assertEq(5,d_.getHands().get(8).total());
        assertEq(5,d_.getHands().get(9).total());
        assertEq(5,d_.getHands().get(10).total());
        assertEq(0,d_.getHands().get(11).total());
        assertFalse(d_.finish());
    }

    @Test
    public void canBeSelected1() {
        assertTrue(dealSample().canBeSelected(0,0));
    }
    @Test
    public void canBeSelected2() {
        assertTrue(dealSample().canBeSelected(1,0));
    }
    @Test
    public void canBeSelected3() {
        assertTrue(dealSample().canBeSelected(2,1));
    }
    @Test
    public void canBeSelected4() {
        assertFalse(dealSample().canBeSelected(0,10));
    }
    @Test
    public void canBeSelected5() {
        assertTrue(dealSample().canBeSelected(4,0));
    }
    @Test
    public void canBeSelected6() {
        assertFalse(dealSample().canBeSelected(10,0));
    }
    @Test
    public void canBeSelected7() {
        assertFalse(dealSample().canBeSelected(11,0));
    }
    @Test
    public void canBeSelected8() {
        assertFalse(playedThreeTimes1().canBeSelected(11,0));
    }
    @Test
    public void canBeSelected9() {
        assertTrue(playedTwice1().canBeSelected(6,0));
    }
    @Test
    public void canBePlayed0() {
        assertFalse(dealSample().canBePlayed(1,0,0));
    }
    @Test
    public void canBePlayed1() {
        assertFalse(dealSample().canBePlayed(0,0,0));
    }
    @Test
    public void canBePlayed2() {
        assertTrue(dealSample().canBePlayed(0,0,1));
    }

    @Test
    public void canBePlayed3() {
        assertFalse(dealSample().canBePlayed(0,0,11));
    }

    @Test
    public void canBePlayed4() {
        assertFalse(dealSample().canBePlayed(5,0,11));
    }

    @Test
    public void canBePlayed5() {
        assertFalse(playedOnce1().canBePlayed(5,0,11));
    }

    @Test
    public void canBePlayed6() {
        assertFalse(dealSample().canBePlayed(1,0,11));
    }

    @Test
    public void canBePlayed7() {
        assertFalse(playedOnce1().canBePlayed(1,0,11));
    }

    @Test
    public void canBePlayed8() {
        assertTrue(playedOnce1().canBePlayed(5,0,1));
    }

    @Test
    public void canBePlayed9() {
        assertTrue(playedTwice1().canBePlayed(1,0,11));
    }
    @Test
    public void canBeSelected10() {
        assertTrue(playedTwice1().canBePlayed(6,0,5));
    }
    @Test
    public void valid1() {
        DealSpider d_ = dealSample();
        d_.getActions().add(action(0,0,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid2() {
        DealSpider d_ = dealSample();
        d_.getActions().add(action(0,10,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid3() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().set(0,CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid4() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().get(11).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid5() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().get(10).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid6() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid7() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().get(1).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid8() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_2}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid9() {
        DealSpider d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.WHITE}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void end() {
        DealSpider d_ = dealSample();
        assertEq(SolitaireType.SPIDER,d_.type());
        tryPlay(d_, action(0, 0, 1));
        tryPlay(d_, action(5, 0, 1));
        tryPlay(d_, action(1, 0, 11));
        tryPlay(d_, action(6, 0, 2));
        tryPlay(d_, action(2, 0, 11));
        tryPlay(d_, action(7, 0, 3));
        tryPlay(d_, action(3, 0, 11));
        tryPlay(d_, action(8, 0, 4));
        tryPlay(d_, action(4, 0, 11));
        tryPlay(d_, action(9, 5, 1));
        tryPlay(d_, action(10, 5, 2));
        tryPlay(d_, action(9, 2, 1));
        tryPlay(d_, action(10, 2, 2));
        tryPlay(d_, action(9, 1, 1));
        tryPlay(d_, action(10, 1, 2));
        tryPlay(d_, action(9, 0, 1));
        tryPlay(d_, action(10, 0, 2));
        tryPlay(d_, action(0, 0, 1));
        tryPlay(d_, action(4, 0, 5));
        tryPlay(d_, action(3, 0, 5));
        tryPlay(d_, action(2, 6, 5));
        tryPlay(d_, action(1, 6, 5));
        tryPlay(d_, action(9, 0, 10));
        tryPlay(d_, action(8, 0, 10));
        tryPlay(d_, action(7, 0, 10));
        tryPlay(d_, action(6, 0, 10));
        tryPlay(d_, action(5, 0, 1));
        tryPlay(d_, action(10, 0, 2));
        tryPlay(d_, action(0, 0, 1));
        tryPlay(d_, action(4, 0, 5));
        tryPlay(d_, action(3, 0, 5));
        tryPlay(d_, action(2, 11, 5));
        tryPlay(d_, action(1, 11, 5));
        tryPlay(d_, action(9, 0, 10));
        tryPlay(d_, action(8, 0, 10));
        tryPlay(d_, action(7, 0, 10));
        tryPlay(d_, action(6, 0, 10));
        tryPlay(d_, action(0, 0, 1));
        tryPlay(d_, action(4, 0, 5));
        tryPlay(d_, action(3, 0, 5));
        tryPlay(d_, action(2, 11, 5));
        tryPlay(d_, action(1, 11, 5));
        tryPlay(d_, action(9, 0, 10));
        tryPlay(d_, action(8, 0, 10));
        tryPlay(d_, action(7, 0, 10));
        tryPlay(d_, action(6, 0, 10));
        tryPlay(d_, action(5, 5, 3));
        tryPlay(d_, action(5, 0, 3));
        tryPlay(d_, action(10, 5, 4));
        tryPlay(d_, action(10, 0, 4));
        tryPlay(d_, action(0, 0, 1));
        tryPlay(d_, action(1, 11, 7));
        tryPlay(d_, action(2, 11, 9));
        tryPlay(d_, action(3, 10, 4));
        tryPlay(d_, action(1, 0, 4));
        tryPlay(d_, action(4, 10, 11));
        tryPlay(d_, action(5, 0, 6));
        tryPlay(d_, action(2, 0, 6));
        tryPlay(d_, action(6, 0, 11));
        tryPlay(d_, action(7, 0, 8));
        tryPlay(d_, action(3, 0, 8));
        tryPlay(d_, action(8, 0, 11));
        tryPlay(d_, action(9, 0, 10));
        tryPlay(d_, action(4, 0, 10));
        assertFalse(d_.finish());
        tryPlay(d_, action(10, 0, 11));
        assertTrue(d_.valid(mes()));
        assertTrue(d_.getError().isEmpty());
        assertTrue(d_.finish());
    }

    private static DealSpider playedThreeTimes1() {
        DealSpider d_ = playedTwice1();
        tryPlay(d_,action(1,0,11));
        return d_;
    }

    private static DealSpider playedTwice1() {
        DealSpider d_ = playedOnce1();
        tryPlay(d_,action(5,0,1));
        return d_;
    }

    private static DealSpider playedOnce1() {
        DealSpider d_ = dealSample();
        tryPlay(d_,action(0,0,1));
        return d_;
    }
    private static DealSpider dealSample() {
        DealSpider d_ = new DealSpider();
        d_.setActions(new CustList<ActionSolitaire>());
        d_.setHands(new CustList<HandSolitaire>());
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_KING);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_KING);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_KING);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_KING);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_6);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_6);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_6);
        d_.getHandsBegin().get(8).ajouter(CardSolitaire.CLUB_6);
        d_.getHandsBegin().get(9).ajouter(CardSolitaire.HEART_6);
        d_.getHandsBegin().get(10).ajouter(CardSolitaire.SPADE_6);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_QUEEN);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_QUEEN);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_QUEEN);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_QUEEN);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_5);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_5);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_5);
        d_.getHandsBegin().get(8).ajouter(CardSolitaire.CLUB_5);
        d_.getHandsBegin().get(9).ajouter(CardSolitaire.HEART_7);
        d_.getHandsBegin().get(10).ajouter(CardSolitaire.SPADE_7);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_JACK);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_JACK);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_JACK);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_JACK);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_4);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_4);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_4);
        d_.getHandsBegin().get(8).ajouter(CardSolitaire.CLUB_4);
        d_.getHandsBegin().get(9).ajouter(CardSolitaire.HEART_10);
        d_.getHandsBegin().get(10).ajouter(CardSolitaire.SPADE_10);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_10);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_10);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_10);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_10);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_3);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_3);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_3);
        d_.getHandsBegin().get(8).ajouter(CardSolitaire.CLUB_3);
        d_.getHandsBegin().get(9).ajouter(CardSolitaire.HEART_9);
        d_.getHandsBegin().get(10).ajouter(CardSolitaire.SPADE_9);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_9);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_9);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_9);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_9);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_2);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_2);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_2);
        d_.getHandsBegin().get(8).ajouter(CardSolitaire.CLUB_2);
        d_.getHandsBegin().get(9).ajouter(CardSolitaire.HEART_8);
        d_.getHandsBegin().get(10).ajouter(CardSolitaire.SPADE_8);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_8);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_8);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_8);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_JACK);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_JACK);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_6);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_9);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_10);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_6);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_9);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_10);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_JACK);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_JACK);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_QUEEN);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_KING);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_QUEEN);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_KING);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_QUEEN);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_KING);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_QUEEN);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_KING);
        d_.fwd();
        assertTrue(d_.valid(mes()));
        return d_;
    }
}
