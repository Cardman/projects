package cards.belote.comparators;

import cards.belote.EquallableBeloteUtil;
import org.junit.Test;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.EnumMap;


public class ComparatorBeloteTest extends EquallableBeloteUtil {

    @Test
    public void compare_GameStrengthLowHandBelote1Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_JACK);
        suit_.ajouter(CardBelote.DIAMOND_9);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.DIAMOND, suits_.get(2));
        assertEq(Suit.HEART, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandBelote2Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_JACK);
        suit_.ajouter(CardBelote.HEART_9);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.HEART, suits_.get(2));
        assertEq(Suit.DIAMOND, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandBelote3Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        suit_.ajouter(CardBelote.DIAMOND_KING);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.HEART, suits_.get(2));
        assertEq(Suit.DIAMOND, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthLowHandBelote4Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        suit_.ajouter(CardBelote.HEART_KING);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthLowHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.SPADE, suits_.get(0));
        assertEq(Suit.CLUB, suits_.get(1));
        assertEq(Suit.DIAMOND, suits_.get(2));
        assertEq(Suit.HEART, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandBelote1Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_JACK);
        suit_.ajouter(CardBelote.DIAMOND_9);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandBelote2Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_JACK);
        suit_.ajouter(CardBelote.HEART_9);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandBelote3Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        suit_.ajouter(CardBelote.DIAMOND_KING);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.DIAMOND, suits_.get(0));
        assertEq(Suit.HEART, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }

    @Test
    public void compare_GameStrengthGreatHandBelote4Test() {
        EnumMap<Suit,HandBelote> hand_;
        hand_ = new EnumMap<Suit,HandBelote>();
        HandBelote suit_ = new HandBelote();
        suit_.ajouter(CardBelote.DIAMOND_1);
        suit_.ajouter(CardBelote.DIAMOND_10);
        hand_.put(Suit.DIAMOND, suit_);
        suit_ = new HandBelote();
        suit_.ajouter(CardBelote.HEART_1);
        suit_.ajouter(CardBelote.HEART_10);
        suit_.ajouter(CardBelote.HEART_KING);
        hand_.put(Suit.HEART, suit_);
        suit_ = new HandBelote();
        hand_.put(Suit.SPADE, suit_);
        hand_.put(Suit.CLUB, suit_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setCouleur(Suit.SPADE);
        bid_.setEnchere(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }
}
