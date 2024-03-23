package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteProgTrickUtilTest extends CommonGameBelote {
    @Test
    public void cartePlusPetitePoints1Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_7, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartePlusPetitePoints2Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_9, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartePlusPetitePoints3Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.DIAMOND);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_1, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartePlusPetitePoints4Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_7, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartePlusPetitePoints5Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_JACK, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartePlusPetitePoints6Test() {
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        assertSame(CardBelote.HEART_1, GameBeloteProgTrick.cartePlusPetitePoints(h_.eclater(rp_,bid_),bid_));
    }
    @Test
    public void cartesRelativementMaitre1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_JACK);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.DIAMOND);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.DIAMOND_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.DIAMOND_KING));
    }
    @Test
    public void cartesRelativementMaitre2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.DIAMOND);
        assertEq(3, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.DIAMOND_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.DIAMOND_KING));
        assertEq(1, rel_.get(2).total());
        assertTrue(rel_.get(2).contient(CardBelote.DIAMOND_8));
    }
    @Test
    public void cartesRelativementMaitre3Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.DIAMOND_JACK,bid_);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.DIAMOND);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.DIAMOND_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.DIAMOND_KING));
    }
    @Test
    public void cartesRelativementMaitre4Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_QUEEN);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
    }
    @Test
    public void cartesRelativementMaitre5Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(3, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
        assertEq(1, rel_.get(2).total());
        assertTrue(rel_.get(2).contient(CardBelote.HEART_8));
    }
    @Test
    public void cartesRelativementMaitre6Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
    }
    @Test
    public void cartesRelativementMaitre7Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_QUEEN, CardBelote.DIAMOND_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
    }
    @Test
    public void cartesRelativementMaitre8Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7, CardBelote.DIAMOND_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(2, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
    }
    @Test
    public void cartesRelativementMaitre9Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7, CardBelote.DIAMOND_7);
        CustList<HandBelote> seqs_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        addCard(poss_,1,CardBelote.DIAMOND_8,bid_);
        addCard(sure_,1,CardBelote.DIAMOND_8,bid_);
        patch(gt_, poss_, sure_,notPlayed_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(Suit.HEART);
        assertEq(3, rel_.size());
        assertEq(1, rel_.get(0).total());
        assertTrue(rel_.get(0).contient(CardBelote.HEART_1));
        assertEq(1, rel_.get(1).total());
        assertTrue(rel_.get(1).contient(CardBelote.HEART_KING));
        assertEq(1, rel_.get(2).total());
        assertTrue(rel_.get(2).contient(CardBelote.HEART_8));
    }
    @Test
    public void discardPartner1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.SPADE_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonAtouts(bid_);
        suits_ = GameBeloteCommon.couleursNonAtoutNonVides(h_,suits_);
        assertSame(CardBelote.HEART_10, gt_.discardPartner());
    }
    @Test
    public void discardPartner2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_8);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.SPADE_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonAtouts(bid_);
        suits_ = GameBeloteCommon.couleursNonAtoutNonVides(h_,suits_);
        assertSame(CardBelote.DIAMOND_8, gt_.discardPartner());
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_1, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_10);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_10, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales5Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleursEgales6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.NO_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.jeuDefausseMaitreJouable());
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_10, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante5Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseCouleurDemandeeSurPartenaireCouleurDominante6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_KING, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.SPADE_9);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_JACK, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        h_.ajouter(CardBelote.DIAMOND_9);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.SPADE_9);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.CLUB_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_JACK, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_9, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales5Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_JACK);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleursEgales6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setBid(BidBelote.ALL_TRUMP);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.CLUB_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.CLUB_7, gt_.defausseAtoutSurPartenaireCouleursEgales(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void ddefausseAtoutSurPartenaireCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_10);
        h_.ajouter(CardBelote.CLUB_1);
        h_.ajouter(CardBelote.CLUB_10);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleurDominante4Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.SPADE_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_10, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleurDominante5Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.CLUB_1);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,bid_));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void defausseAtoutSurPartenaireCouleurDominante6Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,BoolVal.TRUE);
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_KING, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void ddefausseAtoutSurPartenaireCouleurDominante7Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.CLUB_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,bid_));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        IdList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.CLUB_7, gt_.defausseAtoutSurPartenaireCouleurDominante(lead_));
    }
    @Test
    public void discardFoe1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.CLUB_7);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,bid_));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        assertSame(CardBelote.CLUB_7, gt_.discardFoe());
    }
    @Test
    public void discardFoe2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.CLUB_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.HEART_7);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,bid_));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        assertSame(CardBelote.CLUB_JACK, gt_.discardFoe());
    }
    @Test
    public void sauveQuiPeutFigure1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_QUEEN);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_KING, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_QUEEN);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_1, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure3Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_1);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_JACK, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure4Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_10);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_1);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_10, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure5Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_QUEEN);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_10,bid_);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_1, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure6Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_QUEEN);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_7,bid_);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_KING, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure7Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_10, CardBelote.DIAMOND_7);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_QUEEN, sauveQuiPeutFigure(gt_));
    }
    @Test
    public void sauveQuiPeutFigure8Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.HEART);
        bid_.setBid(BidBelote.SUIT);
        bids_.add(bid_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        GameBeloteProgTrick gt_ = gameBeloteProgTrick(r_, bids_, last_, h_, CardBelote.DIAMOND_10, CardBelote.DIAMOND_7);
        CustList<HandBelote> seq_ = seq(bid_, h_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_8,bid_);
        Suit dem_ = Suit.DIAMOND;
        patch(gt_, poss_, sure_,notPlayed_);
        assertSame(CardBelote.DIAMOND_KING, sauveQuiPeutFigure(gt_));
    }

    private CardBelote sauveQuiPeutFigure(GameBeloteProgTrick _gt) {
        return _gt.sauveQuiPeutFigure(_gt.cartesRelativementMaitre(Suit.DIAMOND));
    }

    private CustList<HandBelote> seq(BidBeloteSuit _bid, HandBelote _h) {
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(_bid);
        return _h.eclater(rp_, _bid);
    }

    private GameBeloteProgTrick gameBeloteProgTrick(RulesBelote _r, CustList<BidBeloteSuit> _bids, HandBelote _last, HandBelote _h, CardBelote _carteForte) {
        GameBelote g_ = newGameBelote(_r, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, _bids, _last);
        g_.getProgressingTrick().ajouter(_carteForte);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
//        g_.getProgressingTrick().ajouter(_carteForte);
        return new GameBeloteProgTrick(info_,team_, _h);
    }

    private GameBeloteProgTrick gameBeloteProgTrick(RulesBelote _r, CustList<BidBeloteSuit> _bids, HandBelote _last, HandBelote _h, CardBelote _carteForte, CardBelote _first) {
        GameBelote g_ = newGameBelote(_r, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, _bids, _last);
        g_.getProgressingTrick().ajouter(_first);
        g_.getProgressingTrick().ajouter(_carteForte);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        return new GameBeloteProgTrick(info_,team_, _h);
    }

    private static void addCard(IdMap<Suit, CustList<HandBelote>> _poss, int _p, CardBelote _c, BidBeloteSuit _bid) {
        possCard(_poss, _p, _c, _bid);
    }

    private void patch(GameBeloteProgTrick _gt, IdMap<Suit, CustList<HandBelote>> _poss, IdMap<Suit, CustList<HandBelote>> _sure, Bytes _nonPlayed) {
        BeloteInfoPliEnCours trInf_ = _gt.initInformations();
        trInf_.getCartesPossibles().clear();
        trInf_.getCartesPossibles().addAllEntries(_poss);
        trInf_.getCartesCertaines().clear();
        trInf_.getCartesCertaines().addAllEntries(_sure);
        trInf_.getJoueursNonJoue().clear();
        trInf_.getJoueursNonJoue().addAllElts(_nonPlayed);
    }

    private static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer) {
        return generate();
//        IdMap<Suit,CustList<HandBelote>> e_ = new IdMap<Suit,CustList<HandBelote>>();
//        EnumList<Suit> s_ = new EnumList<Suit>();
//        s_.addAllElts(Suit.couleursOrdinaires());
//        for (Suit s: s_) {
//            CustList<HandBelote> l_ = new CustList<HandBelote>();
//            for (int i = 0; i < _nbPlayer; i++) {
//                l_.add(new HandBelote());
//            }
//            e_.addEntry(s,l_);
//        }
//        return e_;
    }
}
