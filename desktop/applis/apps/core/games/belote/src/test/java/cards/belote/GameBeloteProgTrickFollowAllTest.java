package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import org.junit.Test;

public final class GameBeloteProgTrickFollowAllTest extends CommonGameBelote {
    @Test
    public void fournirAtoutToutAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.ALL_TRUMP);
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
        assertSame(CardBelote.HEART_7, gt_.fournirAtoutToutAtout(infoTr_));
    }
    @Test
    public void fournirAtoutToutAtout2Test() {
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
        assertSame(CardBelote.HEART_KING, gt_.fournirAtoutToutAtout(infoTr_));
    }
    @Test
    public void fournirAtoutToutAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,true);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setEnchere(BidBelote.ALL_TRUMP);
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
        assertSame(CardBelote.HEART_9, gt_.fournirAtoutToutAtout(infoTr_));
    }
}
