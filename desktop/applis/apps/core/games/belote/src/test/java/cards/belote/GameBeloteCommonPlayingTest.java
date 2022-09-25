package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.tsts.TstsBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.*;
import org.junit.Test;

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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        addCard(sure_,2,CardBelote.SPADE_1);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        addCard(sure_,2,CardBelote.SPADE_1);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursCoupeePar((byte) 2, b_, poss_, sure_, couleursNonVides_);
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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(sure_,2,CardBelote.CLUB_9);
        addCard(sure_,2,CardBelote.SPADE_1);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(Bytes.newList((byte) 2), b_, poss_, sure_, couleursNonVides_);
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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(poss_,0,CardBelote.HEART_10);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(Bytes.newList((byte) 2,(byte) 0), b_, poss_, couleursNonVides_);
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
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.HEART_7);
        addCard(poss_,0,CardBelote.HEART_10);
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(Bytes.newList((byte) 2,(byte) 0), b_, poss_, couleursNonVides_);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void joueursSusceptiblesCoupe1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(poss_,2,CardBelote.HEART_7);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_,Suit.HEART, b_.getSuit(),Bytes.newList((byte) 2));
        assertEq(0, pls_.size());
    }
    @Test
    public void joueursSusceptiblesCoupe2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_,Suit.HEART, b_.getSuit(),Bytes.newList((byte) 2));
        assertEq(0, pls_.size());
    }
    @Test
    public void joueursSusceptiblesCoupe3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_, 2, CardBelote.CLUB_9);
        addCard(poss_, 2, CardBelote.SPADE_1);
        Bytes pls_ = GameBeloteCommonPlaying.joueursSusceptiblesCoupe(poss_, Suit.HEART, b_.getSuit(), Bytes.newList((byte) 2));
        assertEq(1, pls_.size());
        assertTrue(pls_.containsObj(2));
    }
    @Test
    public void couleursPouvantEtreCoupees1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.DIAMOND_8);
        addCard(poss_,2,CardBelote.SPADE_1);
        addCard(poss_,2,CardBelote.HEART_7);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(Bytes.newList((byte) 2),poss_, b_.getSuit(), GameBeloteCommon.couleurs());
        assertEq(0, suits_.size());
    }
    @Test
    public void couleursPouvantEtreCoupees2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.HEART_7);
        addCard(poss_,2,CardBelote.DIAMOND_8);
        addCard(poss_,2,CardBelote.CLUB_9);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(Bytes.newList((byte) 2),poss_, b_.getSuit(), GameBeloteCommon.couleurs());
        assertEq(0, suits_.size());
    }
    @Test
    public void couleursPouvantEtreCoupees3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4, b_);
        addCard(poss_,2,CardBelote.DIAMOND_8);
        addCard(poss_,2,CardBelote.CLUB_9);
        addCard(poss_,2,CardBelote.SPADE_1);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(Bytes.newList((byte) 2),poss_, b_.getSuit(), GameBeloteCommon.couleurs());
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
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
        IdList<Suit> couleursNonAtouts_ = nonTrump(b_);
        IdList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, couleursNonAtouts_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursSansCarteMaitresse(cur_, p_, b_, couleursNonVides_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
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
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        assertEq(1, GameBeloteCommonPlaying.getNbLeadingTrumpCards(b_,seqs_,rp_,Suit.CLUB));
    }
    @Test
    public void strictCouleursMaitres1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }


    @Test
    public void strictCouleursMaitres2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void strictCouleursMaitres3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(0, suits_.size());
    }
    @Test
    public void strictCouleursMaitres4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void strictCouleursMaitres5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_JACK);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void strictCouleursMaitres6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_JACK);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void strictCouleursMaitres7Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_9);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(0, suits_.size());
    }
    @Test
    public void strictCouleursMaitres8Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_9);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void strictCouleursMaitres9Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void strictCouleursMaitres10Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void strictCouleursMaitres11Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(0, suits_.size());
    }
    @Test
    public void strictCouleursMaitres12Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.HEART,getOrder(Suit.HEART,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursMaitres1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_JACK);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(3, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_JACK);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres7Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_9);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres8Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_JACK);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.HEART_9);
        cur_.ajouter(CardBelote.DIAMOND_JACK);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
    }
    @Test
    public void couleursMaitres9Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursMaitres10Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }
    @Test
    public void couleursMaitres11Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(0, suits_.size());
    }
    @Test
    public void couleursMaitres12Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_8);
        cur_.ajouter(CardBelote.CLUB_9);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.HEART,getOrder(Suit.HEART,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursMaitres(b_, seqs_, rp_, poss_, (byte) 0);
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.HEART));
    }

    @Test
    public void strictMaitreAtout1Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(!strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout2Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout3Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.CLUB_7);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,getOrder(Suit.SPADE,b_)));
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout4Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.SPADE_JACK);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(!strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout5Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(!strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout6Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(!strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout7Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.SPADE_8);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        p_.ajouter(CardBelote.SPADE_1);
        p_.ajouter(CardBelote.SPADE_10);
        p_.ajouter(CardBelote.SPADE_KING);
        p_.ajouter(CardBelote.SPADE_QUEEN);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout8Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        p_.ajouter(CardBelote.SPADE_1);
        p_.ajouter(CardBelote.SPADE_10);
        p_.ajouter(CardBelote.SPADE_KING);
        p_.ajouter(CardBelote.SPADE_QUEEN);
        p_.ajouter(CardBelote.SPADE_8);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout9Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        p_.ajouter(CardBelote.SPADE_1);
        p_.ajouter(CardBelote.SPADE_10);
        p_.ajouter(CardBelote.SPADE_KING);
        p_.ajouter(CardBelote.SPADE_QUEEN);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }

    @Test
    public void strictMaitreAtout10Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.DIAMOND_7);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        p_.ajouter(CardBelote.SPADE_9);
        p_.ajouter(CardBelote.SPADE_8);
        p_.ajouter(CardBelote.SPADE_1);
        p_.ajouter(CardBelote.SPADE_10);
        p_.ajouter(CardBelote.SPADE_KING);
        p_.ajouter(CardBelote.SPADE_QUEEN);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }
    @Test
    public void strictMaitreAtout11Test() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.NO_TRUMP);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.CLUB_KING);
        cur_.ajouter(CardBelote.SPADE_9);
        cur_.ajouter(CardBelote.CLUB_QUEEN);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = cur_.eclaterTout(rp_, b_);
        IdMap<Suit, CustList<HandBelote>> poss_ = poss(b_, p_, cur_);
        assertTrue(strictMaitreAtout(b_,poss_,(byte)0,seqs_,rp_));
    }
    @Test
    public void carteMaitresseTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.SPADE);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.CLUB_1);
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.SPADE_7);
        cur_.ajouter(CardBelote.DIAMOND_7);
        cur_.ajouter(CardBelote.CLUB_10);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouter(CardBelote.SPADE_JACK);
        p_.ajouter(CardBelote.SPADE_9);
        p_.ajouter(CardBelote.SPADE_8);
        p_.ajouter(CardBelote.SPADE_1);
        p_.ajouter(CardBelote.SPADE_10);
        p_.ajouter(CardBelote.SPADE_KING);
        p_.ajouter(CardBelote.SPADE_QUEEN);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(b_);
        IdList<Suit> n_ = GameBeloteCommon.couleursNonAtoutNonVides(cur_, nonTrump(b_));
        IdMap<Suit, HandBelote> hr_ = cur_.couleurs(b_);
        IdMap<Suit, HandBelote> l_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, b_);
        assertSame(CardBelote.CLUB_1, GameBeloteCommonPlaying.carteMaitresse(b_,n_,l_,cur_,p_));
    }
    @Test
    public void couleursOuvertesTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(DealingBelote.CLASSIC_2_VS_2.getId().getNextPlayer(d_));
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.SPADE_1);
        t_.ajouter(CardBelote.SPADE_10);
        t_.ajouter(CardBelote.SPADE_8);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.CLUB_1);
        t_.ajouter(CardBelote.CLUB_QUEEN);
        t_.ajouter(CardBelote.CLUB_10);
        t_.ajouter(CardBelote.CLUB_8);
        trs_.add(t_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursOuvertes(trs_, nonTrump(b_));
        assertEq(2, suits_.size());
        assertTrue(suits_.containsObj(Suit.SPADE));
        assertTrue(suits_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursNonEntameesTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(DealingBelote.CLASSIC_2_VS_2.getId().getNextPlayer(d_));
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.SPADE_1);
        t_.ajouter(CardBelote.SPADE_10);
        t_.ajouter(CardBelote.SPADE_8);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.CLUB_1);
        t_.ajouter(CardBelote.CLUB_QUEEN);
        t_.ajouter(CardBelote.CLUB_10);
        t_.ajouter(CardBelote.CLUB_8);
        trs_.add(t_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonEntamees(trs_, nonTrump(b_));
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void couleursNonOuvertesNonVidesTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        HandBelote cur_ = new HandBelote();
        cur_.ajouter(CardBelote.DIAMOND_8);
        cur_.ajouter(CardBelote.DIAMOND_7);
        cur_.ajouter(CardBelote.HEART_1);
        cur_.ajouter(CardBelote.HEART_10);
        cur_.ajouter(CardBelote.DIAMOND_1);
        cur_.ajouter(CardBelote.DIAMOND_7);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(DealingBelote.CLASSIC_2_VS_2.getId().getNextPlayer(d_));
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.SPADE_1);
        t_.ajouter(CardBelote.SPADE_10);
        t_.ajouter(CardBelote.SPADE_8);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.CLUB_1);
        t_.ajouter(CardBelote.CLUB_QUEEN);
        t_.ajouter(CardBelote.CLUB_10);
        t_.ajouter(CardBelote.CLUB_8);
        trs_.add(t_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonOuvertesNonVides(cur_,trs_, nonTrump(b_));
        assertEq(1, suits_.size());
        assertTrue(suits_.containsObj(Suit.DIAMOND));
    }
    @Test
    public void toursTest() {
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(DealingBelote.CLASSIC_2_VS_2.getId().getNextPlayer(d_));
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.SPADE_1);
        t_.ajouter(CardBelote.SPADE_10);
        t_.ajouter(CardBelote.SPADE_8);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.CLUB_1);
        t_.ajouter(CardBelote.CLUB_QUEEN);
        t_.ajouter(CardBelote.CLUB_10);
        t_.ajouter(CardBelote.CLUB_8);
        trs_.add(t_);
        CustList<TrickBelote> tours_ = GameBeloteCommonPlaying.tours(Suit.CLUB, trs_);
        assertEq(1, tours_.size());
        assertSame(Suit.CLUB, tours_.first().couleurDemandee());
    }
    private static boolean strictMaitreAtout(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero, IdMap<Suit, CustList<HandBelote>> _suites, IdMap<Suit, HandBelote> _cartesJouees) {
        CustList<HandBelote> seqs_ = GameBeloteCommon.suite(_suites, _bid.getSuit());
        return GameBeloteCommonPlaying.strictMaitreAtout(_bid,_cartesPossibles,_numero,seqs_,_cartesJouees);
    }

    private static IdMap<Suit, CustList<HandBelote>> poss(BidBeloteSuit _b, HandBelote _p, HandBelote _cur) {
        return TstsBelote.pos(_b, _p, _cur);
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
    private static void addCard(IdMap<Suit, CustList<HandBelote>> _poss, int _p, CardBelote _c) {
        HandBelote h_ = _poss.getVal(_c.getId().getCouleur()).get(_p);
//        if (h_.contient(_c)) {
//            return;
//        }
        h_.ajouter(_c);
        h_.trierUnicolore(true);
    }
    private static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer, BidBeloteSuit _b) {
        return TstsBelote.generateMap(_nbPlayer, _b);
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
    private static Order getOrder(Suit _s, BidBeloteSuit _b) {
        return HandBelote.order(_b,_b.getSuit(),_s);
//        if(_b.getCouleurDominante()) {
//            if(_s!= _b.getSuit()) {
//                return Order.SUIT;
//            }
//        } else if(_b.ordreCouleur()) {
//            return Order.SUIT;
//        }
//        return Order.TRUMP;
    }
}
