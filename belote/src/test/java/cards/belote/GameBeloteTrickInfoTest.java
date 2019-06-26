package cards.belote;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EqList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class GameBeloteTrickInfoTest extends CommonGameBelote {
    @Test
    public void neFournitPas1Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        assertTrue(!GameBeloteTrickInfo.neFournitPas(Suit.DIAMOND, (byte) 3,trs_));
    }
    @Test
    public void neFournitPas2Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        assertTrue(!GameBeloteTrickInfo.neFournitPas(Suit.HEART, (byte) 3,trs_));
    }
    @Test
    public void neFournitPas3Test() {
        RulesBelote r_ = new RulesBelote();
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        byte d_ = 2;
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        assertTrue(GameBeloteTrickInfo.neFournitPas(Suit.DIAMOND, (byte) 0,trs_));
    }
    @Test
    public void defausseBelote1Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 1,trs_));
    }
    @Test
    public void defausseBelote2Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 1,trs_));
    }
    @Test
    public void defausseBelote3Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 0,trs_));
    }
    @Test
    public void defausseBelote4Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 0,trs_));
    }
    @Test
    public void defausseBelote5Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 0,trs_));
    }
    @Test
    public void defausseBelote6Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 0,trs_));
    }
    @Test
    public void defausseBelote7Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote8Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.SPADE);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.SPADE_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote9Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote10Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setSousCoupeAdv(false);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote11Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setSousCoupeAdv(true);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote12Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote13Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote14Test() {
        RulesBelote r_ = new RulesBelote();
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote15Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote16Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote17Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
    @Test
    public void defausseBelote18Test() {
        RulesBelote r_ = new RulesBelote();
        r_.setTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        byte d_ = 2;
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.DIAMOND_KING);
        t_.ajouter(CardBelote.SPADE_7);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote game_ = newGameBelote(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(game_);
        assertTrue(!info_.defausseBelote(Suit.DIAMOND, (byte) 2,trs_));
    }
}
