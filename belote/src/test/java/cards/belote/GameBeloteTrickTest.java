package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EqList;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public final class GameBeloteTrickTest extends CommonGameBelote {
    @Test
    public void entame1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.NO_TRUMP,true);
        byte d_ = 2;
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_1);
        HandBelote curHand_ = new HandBelote();
        curHand_.ajouter(CardBelote.HEART_QUEEN);
        curHand_.ajouter(CardBelote.HEART_KING);
        curHand_.ajouter(CardBelote.HEART_1);
        curHand_.ajouter(CardBelote.HEART_10);
        curHand_.ajouter(CardBelote.DIAMOND_1);
        curHand_.ajouter(CardBelote.SPADE_1);
        curHand_.ajouter(CardBelote.SPADE_10);
        curHand_.ajouter(CardBelote.CLUB_1);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.NO_TRUMP);
        b_.setSuit(Suit.UNDEFINED);
        bids_.add(b_);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteBeginTrick gbt_ = newGameBeloteBeginTrick(g_,info_,team_,curHand_);
        assertSame(CardBelote.HEART_1, gbt_.entame());
    }
    @Test
    public void entame2Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        HandBelote curHand_ = new HandBelote();
        curHand_.ajouter(CardBelote.HEART_JACK);
        curHand_.ajouter(CardBelote.HEART_9);
        curHand_.ajouter(CardBelote.HEART_1);
        curHand_.ajouter(CardBelote.HEART_10);
        curHand_.ajouter(CardBelote.DIAMOND_1);
        curHand_.ajouter(CardBelote.SPADE_1);
        curHand_.ajouter(CardBelote.SPADE_10);
        curHand_.ajouter(CardBelote.CLUB_1);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteBeginTrick gbt_ = newGameBeloteBeginTrick(g_,info_,team_,curHand_);
        assertSame(CardBelote.HEART_JACK, gbt_.entame());
    }
    @Test
    public void entame3Test() {
        RulesBelote r_ = new RulesBelote();
        byte d_ = 2;
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        HandBelote curHand_ = new HandBelote();
        curHand_.ajouter(CardBelote.CLUB_10);
        EqList<BidBeloteSuit> bids_ = new EqList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote t_ = new TrickBelote(r_.getRepartition().getNextPlayer(d_));
        t_.ajouter(CardBelote.SPADE_1);
        t_.ajouter(CardBelote.SPADE_9);
        t_.ajouter(CardBelote.SPADE_7);
        t_.ajouter(CardBelote.SPADE_8);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.CLUB_1);
        t_.ajouter(CardBelote.CLUB_7);
        t_.ajouter(CardBelote.CLUB_8);
        t_.ajouter(CardBelote.CLUB_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.DIAMOND_1);
        t_.ajouter(CardBelote.DIAMOND_7);
        t_.ajouter(CardBelote.DIAMOND_8);
        t_.ajouter(CardBelote.DIAMOND_9);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.HEART_JACK);
        t_.ajouter(CardBelote.HEART_7);
        t_.ajouter(CardBelote.CLUB_QUEEN);
        t_.ajouter(CardBelote.CLUB_JACK);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.HEART_9);
        t_.ajouter(CardBelote.HEART_8);
        t_.ajouter(CardBelote.SPADE_QUEEN);
        t_.ajouter(CardBelote.SPADE_JACK);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.HEART_1);
        t_.ajouter(CardBelote.HEART_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_QUEEN);
        t_.ajouter(CardBelote.DIAMOND_JACK);
        trs_.add(t_);
        t_ = new TrickBelote(t_.getRamasseur(b_));
        t_.ajouter(CardBelote.HEART_10);
        t_.ajouter(CardBelote.HEART_KING);
        t_.ajouter(CardBelote.DIAMOND_10);
        t_.ajouter(CardBelote.SPADE_10);
        trs_.add(t_);
        TrickBelote pr_ = new TrickBelote(t_.getRamasseur(b_));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteBeginTrick gbt_ = newGameBeloteBeginTrick(g_,info_,team_,curHand_);
        assertSame(CardBelote.CLUB_10, gbt_.entame());
    }
}
