package cards.solitaire;

import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DealClassicTest extends EquallableSolitaireUtil {
    @Test
    public void deal() {
        DealClassic d_ = new DealClassic();
        d_.setActions(new CustList<ActionSolitaire>());
        d_.setHands(new CustList<HandSolitaire>());
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.deal(DefaultGenerator.oneElt());
        assertTrue(d_.valid(mes()));
        assertEq(12,d_.getHands().size());
        assertEq(24,d_.getHands().get(0).total());
        assertEq(1,d_.getHands().get(1).total());
        assertEq(2,d_.getHands().get(2).total());
        assertEq(3,d_.getHands().get(3).total());
        assertEq(4,d_.getHands().get(4).total());
        assertEq(5,d_.getHands().get(5).total());
        assertEq(6,d_.getHands().get(6).total());
        assertEq(7,d_.getHands().get(7).total());
        assertEq(0,d_.getHands().get(8).total());
        assertEq(0,d_.getHands().get(9).total());
        assertEq(0,d_.getHands().get(10).total());
        assertEq(0,d_.getHands().get(11).total());
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
        assertFalse(dealSample().canBeSelected(2,0));
    }
    @Test
    public void canBeSelected5() {
        assertTrue(dealSample().canBeSelected(4,0));
    }
    @Test
    public void canBeSelected6() {
        assertTrue(playedFiveTimes().canBeSelected(8,1));
    }
    @Test
    public void canBeSelected7() {
        assertFalse(playedFiveTimes().canBeSelected(8,0));
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
        assertFalse(dealSample().canBePlayed(0,0,1));
    }
    @Test
    public void canBePlayed3() {
        assertTrue(dealSample().canBePlayed(0,0,8));
    }
    @Test
    public void canBePlayed4() {
        assertFalse(dealSample().canBePlayed(0,4,8));
    }
    @Test
    public void canBePlayed5() {
        assertFalse(dealSample().canBePlayed(0,15,1));
    }
    @Test
    public void canBePlayed6() {
        assertTrue(dealSample().canBePlayed(0,16,1));
    }
    @Test
    public void canBePlayed7() {
        assertTrue(dealSample().canBePlayed(1,0,7));
    }
    @Test
    public void canBePlayed8() {
        assertFalse(playedOnce1().canBePlayed(0, 0, 0));
    }
    @Test
    public void canBePlayed9() {
        assertTrue(playedOnce1().canBePlayed(0,3,8));
    }
    @Test
    public void canBePlayed10() {
        assertFalse(playedOnce1().canBePlayed(0,4,8));
    }
    @Test
    public void canBePlayed11() {
        assertFalse(playedOnce1().canBePlayed(0,8,8));
    }
    @Test
    public void canBePlayed12() {
        assertTrue(playedOnce1().canBeSelected(8,0));
    }
    @Test
    public void canBePlayed13() {
        assertFalse(playedOnce1().canBeSelected(9,0));
    }
    @Test
    public void canBePlayed14() {
        assertFalse(playedOnce1().canBePlayed(0,0,0));
    }
    @Test
    public void canBePlayed15() {
        assertTrue(playedOnce1().canBePlayed(0,3,8));
    }
    @Test
    public void canBePlayed16() {
        assertFalse(playedOnce1().canBePlayed(0,4,8));
    }
    @Test
    public void canBePlayed17() {
        assertFalse(playedOnce1().canBePlayed(0,8,8));
    }
    @Test
    public void canBePlayed18() {
        assertTrue(playedOnce2().canBePlayed(4,0,1));
    }
    @Test
    public void canBePlayed19() {
        assertFalse(playedOnce2().canBePlayed(2,1,1));
    }
    @Test
    public void canBePlayed20() {
        assertTrue(playedTwice1().canBePlayed(1,0,4));
    }
    @Test
    public void canBePlayed21() {
        assertTrue(dealSample().canBePlayed(0,2,8));
    }
    @Test
    public void canBePlayed23() {
        assertTrue(playedOnce3().canBePlayed(0,5,8));
    }
    @Test
    public void canBePlayed24() {
        assertTrue(playedTwice2().canBePlayed(0,8,8));
    }
    @Test
    public void canBePlayed25() {
        assertTrue(playedThreeTimes().canBePlayed(3,2,8));
    }
    @Test
    public void canBePlayed26() {
        assertFalse(playedThreeTimes().canBePlayed(8,2,2));
    }
    @Test
    public void canBePlayed27() {
        assertTrue(playedFourTimes().canBePlayed(8,3,2));
    }
    @Test
    public void canBePlayed28() {
        assertFalse(playedFourTimes().canBePlayed(8,3,9));
    }

    @Test
    public void valid1() {
        DealClassic d_ = dealSample();
        d_.getActions().add(action(0,0,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid2() {
        DealClassic d_ = dealSample();
        d_.getActions().add(action(2,0,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid3() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().set(0,CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid4() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().get(11).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid5() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().get(7).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid6() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid7() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_2}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid8() {
        DealClassic d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.WHITE}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void end() {
        DealClassic d_ = dealSample();
        assertEq(SolitaireType.CLASSIC,d_.type());
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 9));
        tryPlay(d_, action(0, 0, 10));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 9));
        tryPlay(d_, action(0, 0, 10));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 9));
        tryPlay(d_, action(0, 0, 10));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 9));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 2, 11));
        tryPlay(d_, action(0, 3, 11));
        tryPlay(d_, action(0, 3, 11));
        tryPlay(d_, action(1, 0, 8));
        tryPlay(d_, action(2, 1, 9));
        tryPlay(d_, action(2, 0, 9));
        tryPlay(d_, action(3, 2, 10));
        tryPlay(d_, action(3, 1, 10));
        tryPlay(d_, action(3, 0, 10));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 10));
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(7, 6, 9));
        tryPlay(d_, action(6, 5, 9));
        tryPlay(d_, action(6, 4, 9));
        tryPlay(d_, action(7, 5, 10));
        tryPlay(d_, action(7, 4, 10));
        tryPlay(d_, action(5, 4, 8));
        tryPlay(d_, action(4, 3, 11));
        tryPlay(d_, action(5, 3, 8));
        tryPlay(d_, action(6, 3, 9));
        tryPlay(d_, action(7, 3, 10));
        tryPlay(d_, action(4, 2, 10));
        tryPlay(d_, action(5, 2, 11));
        tryPlay(d_, action(6, 2, 8));
        tryPlay(d_, action(7, 2, 9));
        tryPlay(d_, action(4, 1, 9));
        tryPlay(d_, action(5, 1, 10));
        tryPlay(d_, action(6, 1, 11));
        tryPlay(d_, action(7, 1, 8));
        tryPlay(d_, action(4, 0, 8));
        tryPlay(d_, action(5, 0, 9));
        tryPlay(d_, action(6, 0, 10));
        assertFalse(d_.finish());
        tryPlay(d_, action(7, 0, 11));
        assertTrue(d_.valid(mes()));
        assertTrue(d_.getError().isEmpty());
        assertTrue(d_.finish());
    }

    private static DealClassic dealSample() {
        DealClassic d_ = new DealClassic();
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
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.HEART_6);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_6);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_6);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.SPADE_5);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_5);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.DIAMOND_4);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_KING);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_KING);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_KING);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_KING);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.SPADE_QUEEN);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.DIAMOND_QUEEN);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.CLUB_QUEEN);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.HEART_QUEEN);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.DIAMOND_JACK);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.CLUB_JACK);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.HEART_JACK);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.SPADE_JACK);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.CLUB_10);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_10);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_10);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_10);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.HEART_9);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_9);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_9);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.SPADE_8);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.DIAMOND_8);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.SPADE_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_1);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_2);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_3);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.SPADE_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_4);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_5);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_6);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.DIAMOND_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_7);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.CLUB_9);
        d_.fwd();
        assertTrue(d_.valid(mes()));
        return d_;
    }

    private static DealClassic playedOnce1() {
        DealClassic d_ = dealSample();
        tryPlay(d_,action(0,0,8));
        return d_;
    }

    private static DealClassic playedOnce2() {
        DealClassic d_ = dealSample();
        tryPlay(d_,action(1,0,7));
        return d_;
    }

    private static DealClassic playedOnce3() {
        DealClassic d_ = dealSample();
        tryPlay(d_,action(0,2,8));
        return d_;
    }

    private static DealClassic playedTwice1() {
        DealClassic d_ = playedOnce2();
        tryPlay(d_,action(4,0,1));
        return d_;
    }

    private static DealClassic playedTwice2() {
        DealClassic d_ = playedOnce3();
        tryPlay(d_,action(0,5,8));
        return d_;
    }

    private static DealClassic playedThreeTimes() {
        DealClassic d_ = playedTwice2();
        tryPlay(d_,action(0,8,8));
        return d_;
    }

    private static DealClassic playedFourTimes() {
        DealClassic d_ = playedThreeTimes();
        tryPlay(d_,action(3,2,8));
        return d_;
    }

    private static DealClassic playedFiveTimes() {
        DealClassic d_ = dealSample();
        tryPlay(d_, action(0, 0, 8));
        tryPlay(d_, action(0, 0, 9));
        tryPlay(d_, action(0, 0, 10));
        tryPlay(d_, action(0, 0, 11));
        tryPlay(d_, action(0, 0, 8));
        return d_;
    }

}
