package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.tsts.TstsBelote;
import cards.consts.Order;
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.DIAMOND, sure_, CardBelote.DIAMOND_JACK);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.DIAMOND, sure_, CardBelote.DIAMOND_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.DIAMOND_JACK,bid_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.DIAMOND, sure_, CardBelote.DIAMOND_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.HEART, Suit.HEART, sure_, CardBelote.HEART_QUEEN);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.HEART, Suit.HEART, sure_, CardBelote.HEART_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)0);
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.HEART, Suit.HEART, sure_, CardBelote.HEART_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.HEART, sure_, CardBelote.HEART_QUEEN);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.HEART, sure_, CardBelote.HEART_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seqs_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte)1);
        addCard(poss_,1,CardBelote.HEART_QUEEN,bid_);
        addCard(poss_,1,CardBelote.DIAMOND_8,bid_);
        addCard(sure_,1,CardBelote.DIAMOND_8,bid_);
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seqs_, poss_, notPlayed_, Suit.DIAMOND, Suit.HEART, sure_, CardBelote.HEART_7);
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonAtouts(bid_);
        suits_ = GameBeloteCommon.couleursNonAtoutNonVides(h_,suits_);
        assertSame(CardBelote.HEART_10, gt_.discardPartner(rp_,hr_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.couleursNonAtouts(bid_);
        suits_ = GameBeloteCommon.couleursNonAtoutNonVides(h_,suits_);
        assertSame(CardBelote.DIAMOND_8, gt_.discardPartner(rp_,hr_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_1, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_10, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.CLUB,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.CLUB,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.CLUB,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_10, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.CLUB,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.DIAMOND,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_KING, gt_.defausseCouleurDemandeeSurPartenaireCouleurDominante(rp_,hr_,lead_,Suit.CLUB,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_JACK, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_JACK, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.TRUMP));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.HEART_9, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.TRUMP));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.CLUB_7, gt_.defausseAtoutSurPartenaireCouleursEgales(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_1, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_10, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.SPADE,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.SPADE_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.DIAMOND_KING, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        IdMap<Suit, CustList<HandBelote>> seqs_ = h_.eclaterTout(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(hr_, rp_, bid_);
        EnumList<Suit> suits_ = GameBeloteCommonPlaying.strictCouleursMaitres(bid_, seqs_, rp_, poss_, (byte) 0);
        assertSame(CardBelote.CLUB_7, gt_.defausseAtoutSurPartenaireCouleurDominante(rp_,hr_,lead_,suits_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        assertSame(CardBelote.CLUB_7, gt_.discardFoe(rp_,hr_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        p_.ajouterCartes(HandBelote.couleurComplete(Suit.CLUB,Order.SUIT));
        p_.removeCardIfPresent(CardBelote.CLUB_7);
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        IdMap<Suit, HandBelote> hr_ = h_.couleurs(bid_);
        assertSame(CardBelote.CLUB_JACK, gt_.discardFoe(rp_,hr_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_QUEEN);
        assertSame(CardBelote.DIAMOND_KING,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_QUEEN);
        assertSame(CardBelote.DIAMOND_1,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_1);
        assertSame(CardBelote.DIAMOND_JACK,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_1);
        assertSame(CardBelote.DIAMOND_10,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_10,bid_);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_QUEEN);
        assertSame(CardBelote.DIAMOND_1,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_7,bid_);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_QUEEN);
        assertSame(CardBelote.DIAMOND_KING,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
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
        GameBelote g_ = newGameBelote(r_, new CustList<TrickBelote>(), new TrickBelote((byte)2), 1, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteProgTrick gt_ = new GameBeloteProgTrick(info_,team_,h_);
        HandBelote p_ = new HandBelote();
        IdMap<Suit, HandBelote> rp_ = p_.couleurs(bid_);
        CustList<HandBelote> seq_ = h_.eclater(rp_, bid_);
        IdMap<Suit, CustList<HandBelote>> poss_ = generate(4);
        IdMap<Suit, CustList<HandBelote>> sure_ = generate(4);
        Bytes notPlayed_ = new Bytes();
        notPlayed_.add((byte) 1);
        addCard(poss_,1,CardBelote.DIAMOND_1,bid_);
        Suit dem_ = Suit.DIAMOND;
        CustList<HandBelote> rel_ = gt_.cartesRelativementMaitre(seq_, poss_, notPlayed_, dem_, Suit.DIAMOND, sure_, CardBelote.DIAMOND_10);
        assertSame(CardBelote.DIAMOND_QUEEN,gt_.sauveQuiPeutFigure(poss_,seq_,rel_,notPlayed_,dem_));
    }
    private static void addCard(IdMap<Suit, CustList<HandBelote>> _poss, int _p, CardBelote _c, BidBeloteSuit _bid) {
        possCard(_poss, _p, _c, _bid);
    }

    private static IdMap<Suit,CustList<HandBelote>> generate(int _nbPlayer) {
        return TstsBelote.generate(_nbPlayer);
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
