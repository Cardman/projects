package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumMap;
import org.junit.Test;

import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class GameBeloteCommonTest {
    @Test
    public void cartesMaitresses1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.HEART_9);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(2, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(0, leading_.getVal(Suit.HEART).total());
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(5, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_9));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_10));
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_7));
        assertEq(0, leading_.getVal(Suit.SPADE).total());
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_1));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_1));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_1));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_1));
    }
    @Test
    public void cartesMaitresses6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertEq(4, leading_.size());
        assertEq(1, leading_.getVal(Suit.HEART).total());
        assertTrue(leading_.getVal(Suit.HEART).contient(CardBelote.HEART_JACK));
        assertEq(1, leading_.getVal(Suit.SPADE).total());
        assertTrue(leading_.getVal(Suit.SPADE).contient(CardBelote.SPADE_JACK));
        assertEq(1, leading_.getVal(Suit.DIAMOND).total());
        assertTrue(leading_.getVal(Suit.DIAMOND).contient(CardBelote.DIAMOND_JACK));
        assertEq(1, leading_.getVal(Suit.CLUB).total());
        assertTrue(leading_.getVal(Suit.CLUB).contient(CardBelote.CLUB_JACK));
    }
}
