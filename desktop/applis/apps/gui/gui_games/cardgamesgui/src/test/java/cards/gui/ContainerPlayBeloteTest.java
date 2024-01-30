package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.gui.containers.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class ContainerPlayBeloteTest extends EquallableCardsGuiUtil {
    @Test
    public void b1() {
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        bids_.add(b_);
        assertEq(0,ContainerBelote.index(bids_,b_));
    }
    @Test
    public void b2() {
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.ALL_TRUMP);
        b_.setPoints(1);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        BidBeloteSuit f_ = new BidBeloteSuit();
        f_.setBid(BidBelote.FOLD);
        f_.setPoints(1);
        f_.setSuit(Suit.HEART);
        assertEq(-1,ContainerBelote.index(bids_, f_));
    }
    @Test
    public void b3() {
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.SUIT);
        b_.setPoints(1);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        BidBeloteSuit f_ = new BidBeloteSuit();
        f_.setBid(BidBelote.SUIT);
        f_.setPoints(2);
        f_.setSuit(Suit.HEART);
        assertEq(-1,ContainerBelote.index(bids_, f_));
    }
    @Test
    public void b4() {
        CustList<BidBeloteSuit> bids_ = new CustList<BidBeloteSuit>();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BidBelote.OTHER_SUIT);
        b_.setPoints(1);
        b_.setSuit(Suit.HEART);
        bids_.add(b_);
        BidBeloteSuit f_ = new BidBeloteSuit();
        f_.setBid(BidBelote.OTHER_SUIT);
        f_.setPoints(1);
        f_.setSuit(Suit.DIAMOND);
        assertEq(-1,ContainerBelote.index(bids_, f_));
    }
    @Test
    public void p1() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        SortedPlayers sort_ = rules_.getDealing().getId();
        int first_ = sort_.getNextPlayer(deal_.getDealer());
        int n_ = nextBid(mock_,first_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(0)));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(1)));
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(),bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD))));
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))));
    }

    @Test
    public void p2() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        SortedPlayers sort_ = rules_.getDealing().getId();
        int first_ = sort_.getNextPlayer(deal_.getDealer());
        int n_ = nextBid(mock_,first_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick((AbsButton) csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(),mock_.currentBid())));
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(),bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD))));
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT))));
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT))));
        assertEq(1, NumberUtil.signum(1+ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT))));
    }
    @Test
    public void p3() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        SortedPlayers sort_ = rules_.getDealing().getId();
        int first_ = sort_.getNextPlayer(deal_.getDealer());
        int n_ = nextBid(mock_,first_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        n_ = nextBid(mock_,n_,bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD),sort_);
        nextBid(mock_,n_,bidSuit(Suit.HEART, 0, BidBelote.SUIT),sort_);
        n_ = nextCard(mock_, first_, CardBelote.SPADE_1, sort_);
        n_ = nextCard(mock_, n_, CardBelote.DIAMOND_1, sort_);
        nextCard(mock_, n_, CardBelote.CLUB_1, sort_);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick((AbsButton) csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(),mock_.currentBid())));
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(0)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(1)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(2)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(3)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(4)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(5)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(6)));
        assertTrue(tr2_.containsObj(csb_.getPanelHand().getComponent(7)));
    }

    private ContainerSingleBelote editBelote(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBelote(_mock);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(new GameBelote(GameType.RANDOM, _deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private int nextBid(MockGameBelote _m, int _pl, BidBeloteSuit _bid, SortedPlayers _sort) {
        _m.getBids().add(_bid);
        return _sort.getNextPlayer((byte) _pl);
    }


    private int nextCard(MockGameBelote _m, int _pl, CardBelote _bid, SortedPlayers _sort) {
        _m.getCards().add(_bid);
        return _sort.getNextPlayer((byte) _pl);
    }

    private RulesBelote rules() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private static DealBelote dealStdClassic(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.CLUB_7);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.HEART_7);
        hands_.add(hand_);
        return new DealBelote(hands_, (byte) _dealer);
    }

    private BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }

}
