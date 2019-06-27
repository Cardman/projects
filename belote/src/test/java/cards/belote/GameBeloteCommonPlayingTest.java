package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.Bytes;
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
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
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
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
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
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        addCard(sure_,2,CardBelote.SPADE_1);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
        assertEq(0, suits_.size());
    }
    @Test
    public void couleursNonCoupeeParJoueursTest() {
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
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        addCard(sure_,2,CardBelote.SPADE_1);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(new Bytes((byte) 2), b_, poss_, sure_, couleursNonVides_);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursDefausseeParJoueurs1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(poss_,0,CardBelote.HEART_10);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(new Bytes((byte) 2,(byte) 0), b_, poss_, couleursNonVides_);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursDefausseeParJoueurs2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.HEART_7);
        addCard(poss_,0,CardBelote.HEART_10);
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(new Bytes((byte) 2,(byte) 0), b_, poss_, couleursNonVides_);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void joueursSusceptiblesCoupe1Test() {
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(poss_,2,CardBelote.HEART_7);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_,Suit.HEART,Suit.SPADE,new Bytes((byte) 2));
        assertEq(0, pls_.size());
    }
    @Test
    public void joueursSusceptiblesCoupe2Test() {
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_9);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_,Suit.HEART,Suit.SPADE,new Bytes((byte) 2));
        assertEq(0, pls_.size());
    }
    @Test
    public void joueursSusceptiblesCoupe3Test() {
        EnumMap<Suit, EqList<HandBelote>> poss_ = generate(4);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_,Suit.HEART,Suit.SPADE,new Bytes((byte) 2));
        assertEq(1, pls_.size());
        assertTrue(pls_.containsObj(2));
    }
    @Test
    public void couleursSansCarteMaitresse1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        EnumList<Suit> couleursNonAtouts_ = nonTrump(b_);
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursSansCarteMaitresse(cur_, p_, b_, couleursNonVides_);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void getNbLeadingSuitCards1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.HEART));
    }
    @Test
    public void getNbLeadingSuitCards2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingSuitCards3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(0, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingSuitCards4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(2, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingSuitCards5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        p_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(2, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingSuitCards6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_10);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        p_.ajouter(CardBelote.CLUB_8);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingSuitCards(b_,seqs_,rp_,Suit.CLUB));
    }

    @Test
    public void getNbLeadingTrumpCards1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_JACK);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.HEART));
    }
    @Test
    public void getNbLeadingTrumpCards2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_JACK);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingTrumpCards3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(0, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingTrumpCards4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(2, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingTrumpCards5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        p_.ajouter(CardBelote.CLUB_7);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(2, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void getNbLeadingTrumpCards6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.SPADE_QUEEN);
        cur_.ajouter(CardBelote.DIAMOND_1);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.CLUB_1);
        p_.ajouter(CardBelote.CLUB_10);
        p_.ajouter(CardBelote.CLUB_KING);
        p_.ajouter(CardBelote.CLUB_QUEEN);
        p_.ajouter(CardBelote.CLUB_JACK);
        p_.ajouter(CardBelote.CLUB_9);
        p_.ajouter(CardBelote.CLUB_8);
        EnumMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        EnumMap<Suit, EqList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
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
