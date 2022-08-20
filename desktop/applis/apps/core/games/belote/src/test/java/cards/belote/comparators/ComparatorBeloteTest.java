package cards.belote.comparators;

import cards.belote.EquallableBeloteUtil;
import org.junit.Test;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.IdMap;


public class ComparatorBeloteTest extends EquallableBeloteUtil {

    @Test
    public void compare_GameStrengthLowHandBelote1Test() {
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
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
        IdMap<Suit,HandBelote> hand_;
        hand_ = new IdMap<Suit,HandBelote>();
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
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        suits_.sortElts(new GameStrengthGreatHandBeloteComparator(hand_, bid_));
        assertEq(4, suits_.size());
        assertEq(Suit.HEART, suits_.get(0));
        assertEq(Suit.DIAMOND, suits_.get(1));
        assertEq(Suit.SPADE, suits_.get(2));
        assertEq(Suit.CLUB, suits_.get(3));
    }
}
