package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import org.junit.Test;

public final class GameBeloteProgTrickDiscardTest extends CommonGameBelote {
    @Test
    public void defausseCouleurOrdinaireCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_1);
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_JACK);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireCouleurDominante(infoTr_));
    }
    @Test
    public void defausseCouleurOrdinaireCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_1);
        pr_.ajouter(CardBelote.CLUB_JACK);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireCouleurDominante(infoTr_));
    }
    @Test
    public void defausseCouleurOrdinaireCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireCouleurDominante(infoTr_));
    }
    @Test
    public void defausseAtoutCouleurDominante1Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.CLUB);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_8);
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_JACK);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutCouleurDominante(infoTr_));
    }
    @Test
    public void defausseAtoutCouleurDominante2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.CLUB);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_JACK);
        pr_.ajouter(CardBelote.CLUB_1);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutCouleurDominante(infoTr_));
    }
    @Test
    public void defausseAtoutCouleurDominante3Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.CLUB);
        b_.setEnchere(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutCouleurDominante(infoTr_));
    }
    @Test
    public void defausseCouleurOrdinaireSansAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.NO_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_1);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_8);
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_1);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireSansAtout(infoTr_));
    }
    @Test
    public void defausseCouleurOrdinaireSansAtout2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.NO_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_1);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_1);
        pr_.ajouter(CardBelote.CLUB_JACK);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireSansAtout(infoTr_));
    }
    @Test
    public void defausseCouleurOrdinaireSansAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.NO_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_1);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseCouleurOrdinaireSansAtout(infoTr_));
    }
    @Test
    public void defausseAtoutToutAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.ALL_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_8);
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_JACK);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutToutAtout(infoTr_));
    }
    @Test
    public void defausseAtoutToutAtout2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.ALL_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        pr_.ajouter(CardBelote.CLUB_JACK);
        pr_.ajouter(CardBelote.CLUB_1);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutToutAtout(infoTr_));
    }
    @Test
    public void defausseAtoutToutAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.ALL_TRUMP);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.SPADE_1);
        h_.ajouter(CardBelote.SPADE_7);
        h_.ajouter(CardBelote.SPADE_8);
        h_.ajouter(CardBelote.SPADE_JACK);
        h_.ajouter(CardBelote.DIAMOND_QUEEN);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.CLUB_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_QUEEN);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrick(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.SPADE_7, gt_.defausseAtoutToutAtout(infoTr_));
    }
}
