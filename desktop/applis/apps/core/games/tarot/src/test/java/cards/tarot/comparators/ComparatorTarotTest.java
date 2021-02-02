package cards.tarot.comparators;

import cards.tarot.EquallableTarotUtil;
import code.util.CustList;
import org.junit.Test;

import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.EnumList;


public class ComparatorTarotTest extends EquallableTarotUtil {

    @Test
    public void compare_GameStrengthLowLastHandTarot1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_10);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowLastHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowLastHandTarot2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowLastHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandTarot1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_10);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.HEART, suits_.get(2));
        assertEq(Suit.DIAMOND, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandTarot2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.HEART, suits_.get(2));
        assertEq(Suit.DIAMOND, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandTarot3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_1);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.DIAMOND, suits_.get(2));
        assertEq(Suit.HEART, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandTarot4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.HEART, suits_.get(2));
        assertEq(Suit.DIAMOND, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandTarot5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.DIAMOND, suits_.get(2));
        assertEq(Suit.HEART, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandTarot1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_10);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandTarot2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandTarot3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_1);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandTarot4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandTarot5Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_9);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandTarotComparator(hand_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameSeqLengthTarot1Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        CustList<HandTarot> suitesAtouts_ = hand_.eclaterEnCours(
                new HandTarot().couleurs(), Suit.TRUMP);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        assertEq(3, suitesAtouts_.size());
        assertEq(3, suitesAtouts_.get(0).total());
        assertEq(CardTarot.TRUMP_13, suitesAtouts_.get(0).carte(0));
        assertEq(CardTarot.TRUMP_12, suitesAtouts_.get(0).carte(1));
        assertEq(CardTarot.TRUMP_11, suitesAtouts_.get(0).carte(2));
        assertEq(2, suitesAtouts_.get(1).total());
        assertEq(CardTarot.TRUMP_21, suitesAtouts_.get(1).carte(0));
        assertEq(CardTarot.TRUMP_20, suitesAtouts_.get(1).carte(1));
        assertEq(2, suitesAtouts_.get(2).total());
        assertEq(CardTarot.TRUMP_16, suitesAtouts_.get(2).carte(0));
        assertEq(CardTarot.TRUMP_15, suitesAtouts_.get(2).carte(1));
    }

    @Test
    public void compare_GameSeqLengthTarot2Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        CustList<HandTarot> suitesAtouts_ = hand_.eclaterEnCours(
                new HandTarot().couleurs(), Suit.TRUMP);
        suitesAtouts_.swapIndexes(0, 1);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        assertEq(3, suitesAtouts_.size());
        assertEq(3, suitesAtouts_.get(0).total());
        assertEq(CardTarot.TRUMP_13, suitesAtouts_.get(0).carte(0));
        assertEq(CardTarot.TRUMP_12, suitesAtouts_.get(0).carte(1));
        assertEq(CardTarot.TRUMP_11, suitesAtouts_.get(0).carte(2));
        assertEq(2, suitesAtouts_.get(1).total());
        assertEq(CardTarot.TRUMP_21, suitesAtouts_.get(1).carte(0));
        assertEq(CardTarot.TRUMP_20, suitesAtouts_.get(1).carte(1));
        assertEq(2, suitesAtouts_.get(2).total());
        assertEq(CardTarot.TRUMP_16, suitesAtouts_.get(2).carte(0));
        assertEq(CardTarot.TRUMP_15, suitesAtouts_.get(2).carte(1));
    }

    @Test
    public void compare_GameSeqLengthTarot3Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        CustList<HandTarot> suitesAtouts_ = hand_.eclaterEnCours(
                new HandTarot().couleurs(), Suit.TRUMP);
        suitesAtouts_.swapIndexes(1, 2);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        assertEq(3, suitesAtouts_.size());
        assertEq(3, suitesAtouts_.get(0).total());
        assertEq(CardTarot.TRUMP_13, suitesAtouts_.get(0).carte(0));
        assertEq(CardTarot.TRUMP_12, suitesAtouts_.get(0).carte(1));
        assertEq(CardTarot.TRUMP_11, suitesAtouts_.get(0).carte(2));
        assertEq(2, suitesAtouts_.get(1).total());
        assertEq(CardTarot.TRUMP_21, suitesAtouts_.get(1).carte(0));
        assertEq(CardTarot.TRUMP_20, suitesAtouts_.get(1).carte(1));
        assertEq(2, suitesAtouts_.get(2).total());
        assertEq(CardTarot.TRUMP_16, suitesAtouts_.get(2).carte(0));
        assertEq(CardTarot.TRUMP_15, suitesAtouts_.get(2).carte(1));
    }

    @Test
    public void compare_GameSeqLengthTarot4Test() {
        HandTarot hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.TRUMP_11);
        CustList<HandTarot> suitesAtouts_ = hand_.eclaterEnCours(
                new HandTarot().couleurs(), Suit.TRUMP);
        suitesAtouts_.add(new HandTarot());
        suitesAtouts_.add(new HandTarot());
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        assertEq(5, suitesAtouts_.size());
        assertEq(3, suitesAtouts_.get(0).total());
        assertEq(CardTarot.TRUMP_13, suitesAtouts_.get(0).carte(0));
        assertEq(CardTarot.TRUMP_12, suitesAtouts_.get(0).carte(1));
        assertEq(CardTarot.TRUMP_11, suitesAtouts_.get(0).carte(2));
        assertEq(2, suitesAtouts_.get(1).total());
        assertEq(CardTarot.TRUMP_21, suitesAtouts_.get(1).carte(0));
        assertEq(CardTarot.TRUMP_20, suitesAtouts_.get(1).carte(1));
        assertEq(2, suitesAtouts_.get(2).total());
        assertEq(CardTarot.TRUMP_16, suitesAtouts_.get(2).carte(0));
        assertEq(CardTarot.TRUMP_15, suitesAtouts_.get(2).carte(1));
        assertEq(0, suitesAtouts_.get(3).total());
        assertEq(0, suitesAtouts_.get(4).total());
    }
}
