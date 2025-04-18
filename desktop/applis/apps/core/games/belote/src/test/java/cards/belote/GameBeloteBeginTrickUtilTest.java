package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.IdList;
import code.util.IdMap;
import code.util.CustList;
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
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.DIAMOND_10);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, nonTrump(b_));
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
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.DIAMOND_10);
        p_.ajouter(CardBelote.HEART_8);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, nonTrump(b_));
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
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        IdList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, nonTrump(b_));
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
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, nonTrump(b_));
        IdMap<Suit, HandBelote> l_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
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
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(b_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        IdList<Suit> s_ = GameBeloteCommon.couleursNonAtoutNonVides(h_, nonTrump(b_));
        IdMap<Suit, HandBelote> l_ = GameBeloteCommon.cartesMaitresses(hr_, pr_, b_);
        assertSame(CardBelote.CLUB_7,GameBeloteBeginTrick.ouvrir(b_,s_,hr_,pr_,l_));
    }
    @Test
    public void playedLeading1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        addCard(sure_,3,CardBelote.SPADE_1, b_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        assertTrue(!GameBeloteBeginTrick.playedLeading(b_,3, b_.getSuit(),pr_,sure_));
    }
    @Test
    public void playedLeading2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        addCard(sure_,3,CardBelote.SPADE_9, b_);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        IdMap<Suit, HandBelote> pr_ = p_.couleurs(b_);
        assertTrue(GameBeloteBeginTrick.playedLeading(b_,3, b_.getSuit(),pr_,sure_));
    }
    private static void addCard(IdMap<Suit, CustList<HandBelote>> _poss, int _p, CardBelote _c, BidBeloteSuit _b) {
        HandBelote h_ = _poss.getVal(_c.getId().getCouleur()).get(_p);
//        if (h_.contient(_c)) {
//            return;
//        }
        h_.ajouter(_c);
        h_.trierUnicolore(true,_b);
    }
    private static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer) {
        return generate();
//        IdMap<Suit,CustList<HandBelote>> e_ = new IdMap<Suit,CustList<HandBelote>>();
//        EnumList<Suit> s_ = new EnumList<Suit>();
//        s_.addAllElts(Suit.couleursOrdinaires());
//        for (Suit s: s_) {
//            CustList<HandBelote> l_ = new CustList<HandBelote>();
//            for (int i = 0; i < _nbPlayer; i++) {
//                HandBelote h_ = new HandBelote();
//                if(_b.getCouleurDominante()) {
//                    if(s!= _b.getSuit()) {
//                        h_.setOrdre(Order.SUIT);
//                    }
//                } else if(_b.ordreCouleur()) {
//                    h_.setOrdre(Order.SUIT);
//                }
//                l_.add(h_);
//            }
//            e_.addEntry(s,l_);
//        }
//        return e_;
    }
    private static IdList<Suit> nonTrump(BidBeloteSuit _b) {
        return GameBeloteCommonPlaying.couleursNonAtouts(_b);
//        EnumList<Suit> couleursNonAtouts_=new EnumList<Suit>();
//        if(_b.getCouleurDominante()) {
//            for(Suit couleur_:GameBeloteCommon.couleurs()) {
//                if(couleur_!= _b.getSuit()) {
//                    couleursNonAtouts_.add(couleur_);
//                }
//            }
//            return couleursNonAtouts_;
//        }
//        return GameBeloteCommon.couleurs();
    }
}
