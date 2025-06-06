package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import org.junit.Test;

public final class GameBeloteDeclaringTest extends CommonGameBelote {
    @Test
    public void annulerAnnonces1Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_9);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(0).getHand().total());
    }
    @Test
    public void annulerAnnonces2Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.HEART_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(0).getHand().total());
    }
    @Test
    public void annulerAnnonces3Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(1).getHand().total());
    }
    @Test
    public void annulerAnnonces4Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.annulerAnnonces();
        assertEq(0,decl_.getDeclares().get(0).getHand().total());
    }
    @Test
    public void annulerAnnonces5Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.HEART_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.HEART_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.HEART_9);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(1).getHand().total());
    }
    @Test
    public void annulerAnnonces6Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_9);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(0).getHand().total());
        assertEq(3,decl_.getDeclares().get(1).getHand().total());
    }
    @Test
    public void annulerAnnonces7Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_9);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.getDeclares().get(2).getHand().ajouter(CardBelote.SPADE_JACK);
        decl_.getDeclares().get(2).getHand().ajouter(CardBelote.SPADE_10);
        decl_.getDeclares().get(2).getHand().ajouter(CardBelote.SPADE_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(0).getHand().total());
    }
    @Test
    public void annulerAnnonces8Test() {
        RulesBelote r_ = new RulesBelote();
        int d_ = 2;
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(Suit.HEART);
        b_.setBid(BidBelote.SUIT);
        bids_.add(b_);
        HandBelote h_ = new HandBelote();
        h_.ajouter(CardBelote.HEART_KING);
        h_.ajouter(CardBelote.HEART_7);
        h_.ajouter(CardBelote.HEART_9);
        h_.ajouter(CardBelote.CLUB_8);
        h_.ajouter(CardBelote.HEART_8);
        h_.ajouter(CardBelote.DIAMOND_1);
        h_.ajouter(CardBelote.DIAMOND_KING);
        h_.ajouter(CardBelote.DIAMOND_JACK);
        HandBelote last_ = new HandBelote();
        last_.ajouter(CardBelote.HEART_JACK);
        CustList<TrickBelote> trs_ = new CustList<TrickBelote>();
        TrickBelote pr_ = new TrickBelote(r_.getDealing().getId().getNextPlayer(d_));
        pr_.ajouter(CardBelote.CLUB_10);
        pr_.ajouter(CardBelote.CLUB_7);
        GameBelote g_ = newGameBeloteWithourDecl(r_, trs_, pr_, d_, bids_, last_);
        GameBeloteTeamsRelation team_ = g_.getTeamsRelation();
        GameBeloteTrickInfo info_ = newGameBeloteTrickInfo(g_);
        GameBeloteDeclaring decl_ = new GameBeloteDeclaring(info_,team_,h_,g_.getDeclares());
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_JACK);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_10);
        decl_.getDeclares().get(1).getHand().ajouter(CardBelote.CLUB_9);
        decl_.getDeclares().get(3).getHand().ajouter(CardBelote.SPADE_JACK);
        decl_.getDeclares().get(3).getHand().ajouter(CardBelote.SPADE_10);
        decl_.getDeclares().get(3).getHand().ajouter(CardBelote.SPADE_9);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_JACK);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_10);
        decl_.getDeclares().get(0).getHand().ajouter(CardBelote.DIAMOND_9);
        decl_.annulerAnnonces();
        assertEq(3,decl_.getDeclares().get(1).getHand().total());
    }
}
