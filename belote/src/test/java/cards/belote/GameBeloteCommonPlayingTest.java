package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import org.junit.Test;

import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class GameBeloteCommonPlayingTest extends CommonGameBelote {
    @Test
    public void couleursCoupeePar1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        EnumMap<Suit, EqList<HandBelote>> sure_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_8);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_8);
        addCard(sure_,2,CardBelote.SPADE_1);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursCoupeePar2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        EnumMap<Suit, EqList<HandBelote>> sure_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_8);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_8);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
        assertEq(0, suits_.size());
    }
    @Test
    public void couleursCoupeePar3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        EnumMap<Suit, EqList<HandBelote>> sure_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_8);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_8);
        addCard(sure_,2,CardBelote.SPADE_1);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
        assertEq(0, suits_.size());
    }
    private static EnumList<Suit> nonTrump(BidBeloteSuit _b) {
        EnumList<Suit> couleursNonAtouts_=new EnumList<Suit>();
        if(_b.getCouleurDominante()) {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if(couleur_!=_b.getCouleur()) {
                    couleursNonAtouts_.add(couleur_);
                }
            }
            return couleursNonAtouts_;
        }
        return GameBeloteCommon.couleurs();
    }
    private static void addCard(EnumMap<Suit, EqList<HandBelote>> _poss, int _p, CardBelote _c) {
        HandBelote h_ = _poss.getVal(_c.couleur()).get(_p);
        if (h_.contient(_c)) {
            return;
        }
        h_.ajouter(_c);
        h_.trierUnicolore(true);
    }
    private static EnumMap<Suit,EqList<HandBelote>> generate(int _nbPlayer) {
        EnumMap<Suit,EqList<HandBelote>> e_ = new EnumMap<Suit,EqList<HandBelote>>();
        EnumList<Suit> s_ = new EnumList<Suit>();
        s_.addAllElts(Suit.couleursOrdinaires());
        for (Suit s: s_) {
            EqList<HandBelote> l_ = new EqList<HandBelote>();
            for (int i = 0; i < _nbPlayer; i++) {
                l_.add(new HandBelote());
            }
            e_.addEntry(s,l_);
        }
        return e_;
    }
}
