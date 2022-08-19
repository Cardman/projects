package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteProgTrickFollowAllTest extends CommonGameBelote {
    @Test
    public void fournirAtoutToutAtout1Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
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
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_10);
        pr_.ajouter(CardBelote.HEART_8);
        DealBelote deal_ = new DealBelote(new CustList<HandBelote>(),d_);
        deal_.getDeal().add(create(CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_QUEEN,CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10));
        deal_.getDeal().add(create(CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.DIAMOND_10,CardBelote.DIAMOND_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_7,CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_1,CardBelote.DIAMOND_KING,CardBelote.CLUB_KING,CardBelote.CLUB_8,CardBelote.CLUB_7));
        deal_.getDeal().add(create(CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_QUEEN,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.HEART_KING,CardBelote.HEART_7,CardBelote.DIAMOND_JACK));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, deal_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrickDeal(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_7, gt_.fournirAtoutToutAtout(infoTr_));
    }
    @Test
    public void fournirAtoutToutAtout2Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        bids_.add(new BidBeloteSuit());
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
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
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_10);
        pr_.ajouter(CardBelote.HEART_JACK);
        pr_.ajouter(CardBelote.HEART_8);
        DealBelote deal_ = new DealBelote(new CustList<HandBelote>(),d_);
        deal_.getDeal().add(create(CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_QUEEN,CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10));
        deal_.getDeal().add(create(CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.DIAMOND_10,CardBelote.DIAMOND_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_7,CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_1,CardBelote.DIAMOND_KING,CardBelote.CLUB_KING,CardBelote.CLUB_8,CardBelote.CLUB_7));
        deal_.getDeal().add(create(CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_JACK,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.HEART_KING,CardBelote.HEART_7,CardBelote.DIAMOND_JACK));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, deal_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrickDeal(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_KING, gt_.fournirAtoutToutAtout(infoTr_));
    }
    @Test
    public void fournirAtoutToutAtout3Test() {
        RulesBelote r_ = new RulesBelote();
        r_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        byte d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.UNDEFINED);
        b_.setBid(BidBelote.ALL_TRUMP);
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
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.HEART_7);
        pr_.ajouter(CardBelote.DIAMOND_7);
        DealBelote deal_ = new DealBelote(new CustList<HandBelote>(),d_);
        deal_.getDeal().add(create(CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8));
        deal_.getDeal().add(create(CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_1,CardBelote.DIAMOND_KING,CardBelote.CLUB_KING,CardBelote.CLUB_8,CardBelote.CLUB_7));
        deal_.getDeal().add(create(CardBelote.HEART_10,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.HEART_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.DIAMOND_10,CardBelote.DIAMOND_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.DIAMOND_8,CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_QUEEN));
        deal_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.DIAMOND_8,CardBelote.CLUB_JACK,CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.DIAMOND_JACK,CardBelote.HEART_10,CardBelote.HEART_KING,CardBelote.HEART_QUEEN));
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, deal_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteProgTrick gt_ = newGameBeloteProgTrickDeal(g_, info_, team_, h_);
        BeloteInfoPliEnCours infoTr_ = gt_.initInformations();
        assertSame(CardBelote.HEART_9, gt_.fournirAtoutToutAtout(infoTr_));
    }
}
