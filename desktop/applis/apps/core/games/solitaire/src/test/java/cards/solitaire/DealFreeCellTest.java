package cards.solitaire;

import code.maths.montecarlo.*;
import code.util.*;
import org.junit.Test;

public final class DealFreeCellTest extends EquallableSolitaireUtil {
    @Test
    public void deal() {
        DealFreeCell d_ = new DealFreeCell();
        assertEq(SolitaireType.FREECELL,d_.type());
        d_.setActions(new CustList<ActionSolitaire>());
        d_.setHands(new CustList<HandSolitaire>());
        d_.setHandsBegin(new CustList<HandSolitaire>());
        d_.deal(DefaultGenerator.oneElt());
        assertTrue(d_.valid(mes()));
        assertEq(16,d_.getHands().size());
        assertEq(7,d_.getHands().get(0).total());
        assertEq(7,d_.getHands().get(1).total());
        assertEq(7,d_.getHands().get(2).total());
        assertEq(7,d_.getHands().get(3).total());
        assertEq(6,d_.getHands().get(4).total());
        assertEq(6,d_.getHands().get(5).total());
        assertEq(6,d_.getHands().get(6).total());
        assertEq(6,d_.getHands().get(7).total());
        assertEq(0,d_.getHands().get(8).total());
        assertEq(0,d_.getHands().get(9).total());
        assertEq(0,d_.getHands().get(10).total());
        assertEq(0,d_.getHands().get(11).total());
        assertEq(0,d_.getHands().get(12).total());
        assertEq(0,d_.getHands().get(13).total());
        assertEq(0,d_.getHands().get(14).total());
        assertEq(0,d_.getHands().get(15).total());
        assertFalse(d_.finish());
    }

    @Test
    public void canBeSelected1() {
        assertTrue(dealSample().canBeSelected(0,6));
    }
    @Test
    public void canBeSelected2() {
        assertTrue(dealSample().canBeSelected(4,5));
    }
    @Test
    public void canBeSelected3() {
        assertFalse(dealSample().canBeSelected(4,0));
    }
    @Test
    public void canBeSelected4() {
        assertFalse(dealSample().canBeSelected(8,0));
    }
    @Test
    public void canBeSelected5() {
        assertTrue(playedOnce1().canBeSelected(8, 0));
    }
    @Test
    public void canBeSelected6() {
        assertTrue(emptyHalfExceptAces().canBeSelected(4, 0));
    }
    @Test
    public void canBeSelected7() {
        assertTrue(emptyHalf().canBeSelected(12, 0));
    }
    @Test
    public void canBeSelected8() {
        assertTrue(emptyHalf().canBeSelected(0, 11));
    }
    @Test
    public void canBeSelected9() {
        assertFalse(emptyHalfStacking().canBeSelected(12, 0));
    }
    @Test
    public void canBePlayed0() {
        assertFalse(dealSample().canBePlayed(0,0,0));
    }
    @Test
    public void canBePlayed1() {
        assertTrue(dealSample().canBePlayed(0,6,8));
    }
    @Test
    public void canBePlayed2() {
        assertTrue(dealSample().canBePlayed(4,5,8));
    }
    @Test
    public void canBePlayed3() {
        assertTrue(dealSample().canBePlayed(4,5,1));
    }
    @Test
    public void canBePlayed4() {
        assertFalse(dealSample().canBePlayed(4,5,0));
    }
    @Test
    public void canBePlayed5() {
        assertFalse(dealSample().canBePlayed(4,5,12));
    }
    @Test
    public void canBePlayed6() {
        assertTrue(emptyHalfExceptAces().canBePlayed(4, 0,12));
    }
    @Test
    public void canBePlayed7() {
        assertTrue(emptyHalf().canBePlayed(0, 11,4));
    }
    @Test
    public void canBePlayed8() {
        assertTrue(emptyHalf().canBePlayed(0, 11,13));
    }
    @Test
    public void canBePlayed9() {
        assertFalse(emptyHalf().canBePlayed(0, 11,12));
    }
    @Test
    public void canBePlayed10() {
        assertFalse(emptyHalf().canBePlayed(13, 1,12));
    }
    @Test
    public void valid1() {
        DealFreeCell d_ = dealSample();
        d_.getActions().add(action(4,5,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid2() {
        DealFreeCell d_ = dealSample();
        d_.getActions().add(action(4,0,0));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid3() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().set(0,CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid4() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().get(11).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid5() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().get(15).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid6() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().get(7).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid7() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().get(0).getCards().add(CardSolitaire.CLUB_2);
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid8() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.CLUB_2}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void valid9() {
        DealFreeCell d_ = dealSample();
        d_.getHandsBegin().clear();
        d_.getHandsBegin().add(HandSolitaire.create(new CardSolitaire[]{CardSolitaire.WHITE}));
        assertFalse(d_.valid(mes()));
        assertFalse(d_.getError().isEmpty());
    }
    @Test
    public void end() {
        DealFreeCell d_ = dealSample();
        assertEq(SolitaireType.FREECELL,d_.type());
        tryPlay(d_,action(4,5,1));
        tryPlay(d_,action(5,5,0));
        tryPlay(d_,action(6,5,3));
        tryPlay(d_,action(7,5,2));
        tryPlay(d_,action(4,4,2));
        tryPlay(d_,action(5,4,1));
        tryPlay(d_,action(6,4,0));
        tryPlay(d_,action(7,4,3));
        tryPlay(d_,action(4,3,3));
        tryPlay(d_,action(5,3,2));
        tryPlay(d_,action(6,3,1));
        tryPlay(d_,action(7,3,0));
        tryPlay(d_,action(4,2,0));
        tryPlay(d_,action(5,2,3));
        tryPlay(d_,action(6,2,2));
        tryPlay(d_,action(7,2,1));
        tryPlay(d_,action(4,1,1));
        tryPlay(d_,action(5,1,0));
        tryPlay(d_,action(6,1,3));
        tryPlay(d_,action(7,1,2));
        tryPlay(d_,action(4,0,12));
        tryPlay(d_,action(5,0,13));
        tryPlay(d_,action(6,0,14));
        tryPlay(d_,action(7,0,15));
        tryPlay(d_,action(0,11,13));
        tryPlay(d_,action(1,11,12));
        tryPlay(d_,action(2,11,15));
        tryPlay(d_,action(3,11,14));
        tryPlay(d_,action(0,10,12));
        tryPlay(d_,action(1,10,15));
        tryPlay(d_,action(2,10,14));
        tryPlay(d_,action(3,10,13));
        tryPlay(d_,action(0,9,15));
        tryPlay(d_,action(1,9,14));
        tryPlay(d_,action(2,9,13));
        tryPlay(d_,action(3,9,12));
        tryPlay(d_,action(0,8,14));
        tryPlay(d_,action(1,8,13));
        tryPlay(d_,action(2,8,12));
        tryPlay(d_,action(3,8,15));
        tryPlay(d_,action(0,7,13));
        tryPlay(d_,action(1,7,12));
        tryPlay(d_,action(2,7,15));
        tryPlay(d_,action(3,7,14));
        tryPlay(d_,action(0,6,12));
        tryPlay(d_,action(1,6,13));
        tryPlay(d_,action(2,6,14));
        tryPlay(d_,action(3,6,15));
        tryPlay(d_,action(0,5,12));
        tryPlay(d_,action(1,5,13));
        tryPlay(d_,action(2,5,14));
        tryPlay(d_,action(3,5,15));
        tryPlay(d_,action(0,4,12));
        tryPlay(d_,action(1,4,13));
        tryPlay(d_,action(2,4,14));
        tryPlay(d_,action(3,4,15));
        tryPlay(d_,action(0,3,12));
        tryPlay(d_,action(1,3,13));
        tryPlay(d_,action(2,3,14));
        tryPlay(d_,action(3,3,15));
        tryPlay(d_,action(0,2,12));
        tryPlay(d_,action(1,2,13));
        tryPlay(d_,action(2,2,14));
        tryPlay(d_,action(3,2,15));
        tryPlay(d_,action(0,1,12));
        tryPlay(d_,action(1,1,13));
        tryPlay(d_,action(2,1,14));
        tryPlay(d_,action(3,1,15));
        tryPlay(d_,action(0,0,12));
        tryPlay(d_,action(1,0,13));
        tryPlay(d_,action(2,0,14));
        assertFalse(d_.finish());
        tryPlay(d_, action(3, 0, 15));
        assertTrue(d_.valid(mes()));
        assertTrue(d_.getError().isEmpty());
        assertTrue(d_.finish());
    }
    private static DealFreeCell playedOnce1() {
        DealFreeCell d_ = dealSample();
        tryPlay(d_,action(4,5,8));
        return d_;
    }

    private static DealFreeCell emptyHalfExceptAces() {
        DealFreeCell d_ = dealSample();
        tryPlay(d_,action(4,5,1));
        tryPlay(d_,action(5,5,0));
        tryPlay(d_,action(6,5,3));
        tryPlay(d_,action(7,5,2));
        tryPlay(d_,action(4,4,2));
        tryPlay(d_,action(5,4,1));
        tryPlay(d_,action(6,4,0));
        tryPlay(d_,action(7,4,3));
        tryPlay(d_,action(4,3,3));
        tryPlay(d_,action(5,3,2));
        tryPlay(d_,action(6,3,1));
        tryPlay(d_,action(7,3,0));
        tryPlay(d_,action(4,2,0));
        tryPlay(d_,action(5,2,3));
        tryPlay(d_,action(6,2,2));
        tryPlay(d_,action(7,2,1));
        tryPlay(d_,action(4,1,1));
        tryPlay(d_,action(5,1,0));
        tryPlay(d_,action(6,1,3));
        tryPlay(d_,action(7,1,2));
        return d_;
    }

    private static DealFreeCell emptyHalf() {
        DealFreeCell d_ = dealSample();
        tryPlay(d_,action(4,5,1));
        tryPlay(d_,action(5,5,0));
        tryPlay(d_,action(6,5,3));
        tryPlay(d_,action(7,5,2));
        tryPlay(d_,action(4,4,2));
        tryPlay(d_,action(5,4,1));
        tryPlay(d_,action(6,4,0));
        tryPlay(d_,action(7,4,3));
        tryPlay(d_,action(4,3,3));
        tryPlay(d_,action(5,3,2));
        tryPlay(d_,action(6,3,1));
        tryPlay(d_,action(7,3,0));
        tryPlay(d_,action(4,2,0));
        tryPlay(d_,action(5,2,3));
        tryPlay(d_,action(6,2,2));
        tryPlay(d_,action(7,2,1));
        tryPlay(d_,action(4,1,1));
        tryPlay(d_,action(5,1,0));
        tryPlay(d_,action(6,1,3));
        tryPlay(d_,action(7,1,2));
        tryPlay(d_,action(4,0,12));
        tryPlay(d_,action(5,0,13));
        tryPlay(d_,action(6,0,14));
        tryPlay(d_,action(7,0,15));
        return d_;
    }

    private static DealFreeCell emptyHalfStacking() {
        DealFreeCell d_ = dealSample();
        tryPlay(d_,action(4,5,1));
        tryPlay(d_,action(5,5,0));
        tryPlay(d_,action(6,5,3));
        tryPlay(d_,action(7,5,2));
        tryPlay(d_,action(4,4,2));
        tryPlay(d_,action(5,4,1));
        tryPlay(d_,action(6,4,0));
        tryPlay(d_,action(7,4,3));
        tryPlay(d_,action(4,3,3));
        tryPlay(d_,action(5,3,2));
        tryPlay(d_,action(6,3,1));
        tryPlay(d_,action(7,3,0));
        tryPlay(d_,action(4,2,0));
        tryPlay(d_,action(5,2,3));
        tryPlay(d_,action(6,2,2));
        tryPlay(d_,action(7,2,1));
        tryPlay(d_,action(4,1,1));
        tryPlay(d_,action(5,1,0));
        tryPlay(d_,action(6,1,3));
        tryPlay(d_,action(7,1,2));
        tryPlay(d_,action(4,0,12));
        tryPlay(d_,action(5,0,13));
        tryPlay(d_,action(6,0,14));
        tryPlay(d_,action(7,0,15));
        tryPlay(d_,action(0,11,13));
        tryPlay(d_,action(1,11,12));
        tryPlay(d_,action(2,11,15));
        tryPlay(d_,action(3,11,14));
        return d_;
    }
    private static DealFreeCell dealSample() {
        DealFreeCell d_ = new DealFreeCell();
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
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().add(new HandSolitaire());
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_KING);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_QUEEN);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_JACK);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_10);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_9);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_8);
        d_.getHandsBegin().get(0).ajouter(CardSolitaire.HEART_7);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_KING);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_QUEEN);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_JACK);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_10);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_9);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_8);
        d_.getHandsBegin().get(1).ajouter(CardSolitaire.SPADE_7);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_KING);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_QUEEN);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_JACK);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_10);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_9);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_8);
        d_.getHandsBegin().get(2).ajouter(CardSolitaire.DIAMOND_7);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_KING);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_QUEEN);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_JACK);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_10);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_9);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_8);
        d_.getHandsBegin().get(3).ajouter(CardSolitaire.CLUB_7);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_1);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_2);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_3);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_4);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_5);
        d_.getHandsBegin().get(4).ajouter(CardSolitaire.HEART_6);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_1);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_2);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_3);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_4);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_5);
        d_.getHandsBegin().get(5).ajouter(CardSolitaire.SPADE_6);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_1);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_2);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_3);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_4);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_5);
        d_.getHandsBegin().get(6).ajouter(CardSolitaire.DIAMOND_6);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_1);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_2);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_3);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_4);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_5);
        d_.getHandsBegin().get(7).ajouter(CardSolitaire.CLUB_6);
        d_.fwd();
        assertTrue(d_.valid(mes()));
        return d_;
    }
}
