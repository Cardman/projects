package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EqList;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public final class GameBeloteProgTrickFollowTrumpTest extends CommonGameBelote {
    @Test
    public void followTrumpPartner1Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_10);
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_8);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_KING, gt_.followTrumpPartner(infoTr_));
    }
    @Test
    public void followTrumpPartner2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_QUEEN);
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_8);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_7, gt_.followTrumpPartner(infoTr_));
    }
    @Test
    public void followTrumpPartner3Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_QUEEN);
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_7, gt_.followTrumpPartner(infoTr_));
    }
    @Test
    public void followTrumpPartner4Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_7, gt_.followTrumpPartner(infoTr_));
    }
    @Test
    public void followTrumpPartner5Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.TEAM, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_9, gt_.followTrumpPartner(infoTr_));
    }
    @Test
    public void playDefaultTrump1Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_7);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_9, gt_.playDefaultTrump(infoTr_));
    }
    @Test
    public void playDefaultTrump2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_7);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(PossibleTrickWinner.UNKNOWN, GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(infoTr_));
        assertSame(CardBelote.HEART_8, gt_.playDefaultTrump(infoTr_));
    }
    @Test
    public void fournirAtoutCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_10);
        pr_.ajouter(CardBelote.HEART_8);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_7, gt_.fournirAtoutCouleurDominante(infoTr_));
    }
    @Test
    public void fournirAtoutCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_10);
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_8);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_KING, gt_.fournirAtoutCouleurDominante(infoTr_));
    }
    @Test
    public void fournirAtoutCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.HEART_1);
        h_.ajouter(CardBelote.CLUB_KING);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.CLUB_7);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_7);
        pr_.ajouter(CardBelote.DIAMOND_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_9, gt_.fournirAtoutCouleurDominante(infoTr_));
    }
}
