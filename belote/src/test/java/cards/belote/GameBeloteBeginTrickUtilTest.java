package cards.belote;

import static org.junit.Assert.assertSame;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.EnumMap;
import org.junit.Test;

public final class GameBeloteBeginTrickUtilTest extends CommonGameBelote {
    @Test
    public void faireCouperAppeleTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.DIAMOND_10);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, GameBeloteCommon.couleurs());
        assertSame(CardBelote.HEART_7,GameBeloteBeginTrick.faireCouperAppele(s_,hr_,pr_));
    }
    @Test
    public void faireCouperTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.DIAMOND_10);
        p_.ajouter(CardBelote.HEART_8);
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, GameBeloteCommon.couleurs());
        assertSame(CardBelote.HEART_7,GameBeloteBeginTrick.faireCouper(b_,s_,hr_,pr_));
    }
    @Test
    public void ouvrirCouleurTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, GameBeloteCommon.couleurs());
        assertSame(CardBelote.CLUB_8,GameBeloteBeginTrick.ouvrirCouleur(b_,s_,hr_));
    }
    @Test
    public void ouvrir1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_10);
        h_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, GameBeloteCommon.couleurs());
        EnumMap<Suit, HandBelote> l_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertSame(CardBelote.HEART_1,GameBeloteBeginTrick.ouvrir(b_,s_,hr_,pr_,l_));
    }
    @Test
    public void ouvrir2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        EnumList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, GameBeloteCommon.couleurs());
        EnumMap<Suit, HandBelote> l_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertSame(CardBelote.CLUB_7,GameBeloteBeginTrick.ouvrir(b_,s_,hr_,pr_,l_));
    }
}
