package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import code.gui.*;
import code.gui.events.*;
import code.mock.*;
import code.stream.*;
import code.util.*;
import code.util.core.*;
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
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }

    @Test
    public void p2() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(4,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.OTHER_SUIT)))));
    }
    @Test
    public void p3() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p4() {
        RulesBelote rules_ = rulesOverBid();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(4,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP)))));
        assertFalse(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
        assertFalse(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP)))));
    }
    @Test
    public void p5() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(3);
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p6() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p7() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = deal1Classic(2);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.SPADE, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.DIAMOND_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(3, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertFalse(csb_.getBeloteRebelote().isEnabled());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.DIAMOND_9)));
    }
    @Test
    public void p8() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_KING);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p9() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_QUEEN);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p10() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(0,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p11() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(10, tr2_.size());
        assertFalse(csb_.getBeloteDeclare().isSelected());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(csb_.getBeloteDeclare()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p12() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p13() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getNextTrick()));
    }
    @Test
    public void p14() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.DIAMOND_JACK);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(7, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p15() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_KING);
        nextCard(mock_, CardBelote.HEART_QUEEN);
        nextCard(mock_, CardBelote.SPADE_7);
        nextCard(mock_, CardBelote.DIAMOND_7);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(2,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(6, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p16() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_QUEEN);
        nextCard(mock_, CardBelote.HEART_KING);
        nextCard(mock_, CardBelote.SPADE_7);
        nextCard(mock_, CardBelote.DIAMOND_7);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(2,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(6, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p17() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_KING);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.DIAMOND_QUEEN);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(7, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p18() {
        RulesBelote rules_ = rulesDeclare();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_QUEEN);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.DIAMOND_KING);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(7, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p19() {
        RulesBelote rules_ = rulesOverBid();
        DealBelote deal_ = deal2Classic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP));
        nextCard(mock_, CardBelote.HEART_8);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
    }
    @Test
    public void p20() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(14, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
    }
    @Test
    public void p21() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton());
        assertFalse(csb_.getBidOk().isEnabled());
        CustList<AbsMouseListenerIntRel> rel_ = csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(15, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
        assertTrue(tr_.containsObj(csb_.getBidOk()));
    }
    @Test
    public void p22() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        CustList<AbsMouseListenerIntRel> rel_ = csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        assertFalse(csb_.getBidOk().isEnabled());
        tryClick(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(15, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
        assertTrue(tr_.containsObj(csb_.getBidOk()));
    }
    @Test
    public void p23() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 90, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_,mock_);
        tryAnimate(csb_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getMainCardGame()));
    }
    @Test
    public void p24() {
        RulesBelote rules_ = rulesDealAllOverBid();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
    }
    @Test
    public void p25() {
        RulesBelote rules_ = rulesDealAllOverBid();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton());
        assertFalse(csb_.getBidOk().isEnabled());
        CustList<AbsMouseListenerIntRel> rel_ = csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP))).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(17, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
        assertTrue(tr_.containsObj(csb_.getBidOk()));
    }
    @Test
    public void p26() {
        RulesBelote rules_ = rulesDealAllOverBid();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        CustList<AbsMouseListenerIntRel> rel_ = csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP))).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        assertFalse(csb_.getBidOk().isEnabled());
        tryClick(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(17, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
        assertTrue(tr_.containsObj(csb_.getBidOk()));
    }
    @Test
    public void p27() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 90, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_,mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        assertEq(3,csb_.partieBelote().getProgressingTrick().total());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p28() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 162, BidBelote.SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_,mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        assertEq(0,csb_.partieBelote().getProgressingTrick().getEntameur());
        assertEq(0,csb_.partieBelote().getProgressingTrick().total());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }

    @Test
    public void p29() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p30() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdAll(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.getFold());
        tryAnimate(csb_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p31() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p32() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        ContainerSingleBelote csb_ = editBeloteOtherDisplay(rules_, deal_, mock_);
        dealMock(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
    }

    @Test
    public void p33() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        mock_.getPossible().addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        mock_.getPossible().addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        IdMap<Suit, CustList<HandBelote>> id_ = new IdMap<Suit, CustList<HandBelote>>();
        mock_.getSure().addEntry(Hypothesis.SURE, id_);
        id_.addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        id_.addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p34() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        mock_.getPossible().addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        mock_.getPossible().addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        IdMap<Suit, CustList<HandBelote>> id_ = new IdMap<Suit, CustList<HandBelote>>();
        mock_.getSure().addEntry(Hypothesis.SURE, id_);
        id_.addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        id_.addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p35() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = deal1Classic(2);
        MockGameBelote mock_ = new MockGameBelote();
        mock_.getPossible().addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        mock_.getPossible().addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        IdMap<Suit, CustList<HandBelote>> id_ = new IdMap<Suit, CustList<HandBelote>>();
        mock_.getSure().addEntry(Hypothesis.SURE, id_);
        id_.addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        id_.addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.DIAMOND, 0, BidBelote.OTHER_SUIT));
        nextCard(mock_, CardBelote.DIAMOND_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p36() {
        RulesBelote rules_ = rulesOverBid();
        DealBelote deal_ = deal2Classic(0);
        MockGameBelote mock_ = new MockGameBelote();
        mock_.getPossible().addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        mock_.getPossible().addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        mock_.getPossible().addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        IdMap<Suit, CustList<HandBelote>> id_ = new IdMap<Suit, CustList<HandBelote>>();
        mock_.getSure().addEntry(Hypothesis.SURE, id_);
        id_.addEntry(Suit.HEART, hand(new HandBelote(),new HandBelote(),create(CardBelote.HEART_KING),create(CardBelote.HEART_QUEEN)));
        id_.addEntry(Suit.SPADE, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.DIAMOND, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        id_.addEntry(Suit.CLUB, hand(new HandBelote(),new HandBelote(),new HandBelote(),new HandBelote()));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP));
        nextCard(mock_, CardBelote.HEART_8);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p37() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p38() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p39() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p40() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p41() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getMainCardGame()));
    }
    @Test
    public void p42() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p43() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p44() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p45() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_1);
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p46() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_1);
        play(gb_, CardBelote.DIAMOND_1);
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p47() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_1);
        play(gb_, CardBelote.DIAMOND_1);
        play(gb_, CardBelote.CLUB_1);
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p48() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassicNext(3);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p49() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassicNext(3);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p50() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassicNext(3);
        GameBelote gb_ = edited(deal_, rules_);
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p51() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_1);
        play(gb_, CardBelote.DIAMOND_1);
        play(gb_, CardBelote.CLUB_1);
        play(gb_, CardBelote.HEART_1);
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
//        tryClickNextPhase(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p52() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_1);
        play(gb_, CardBelote.DIAMOND_1);
        play(gb_, CardBelote.CLUB_1);
        play(gb_, CardBelote.HEART_1);
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr2_.size());
        assertFalse(csb_.getBeloteRebelote().isSelected());
        assertTrue(tr2_.containsObj(csb_.getBeloteRebelote()));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr2_.containsObj(component(csb_,CardBelote.HEART_7)));
    }
    @Test
    public void p53() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        fullPlayedDeal(gb_);
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
    }
    @Test
    public void p54() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getTricksHands());
        assertEq(0,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getSelectedIndex());
        assertEq(-1,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getSelectedIndex());
        assertEq(5, hand(csb_, 0).total());
        assertEq(5, hand(csb_, 1).total());
        assertEq(5, hand(csb_, 2).total());
        assertEq(5, hand(csb_, 3).total());
    }
    @Test
    public void p55() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),1);
        assertEq(8, hand(csb_, 0).total());
        assertEq(8, hand(csb_, 1).total());
        assertEq(8, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }
    @Test
    public void p56() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        assertEq(7, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(7, hand(csb_, 3).total());
    }
    @Test
    public void p57() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),0);
        assertEq(8, hand(csb_, 0).total());
        assertEq(8, hand(csb_, 1).total());
        assertEq(8, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }
    @Test
    public void p58() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),1);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(8, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }
    @Test
    public void p59() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),2);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }
    @Test
    public void p60() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),3);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(7, hand(csb_, 3).total());
    }
    @Test
    public void p61() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),4);
        assertEq(7, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(7, hand(csb_, 3).total());
    }
    @Test
    public void p62() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.SPADE_10);
        nextCard(mock_, CardBelote.DIAMOND_10);
        nextCard(mock_, CardBelote.CLUB_10);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        assertEq(7, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(7, hand(csb_, 3).total());
    }
    @Test
    public void p63() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.SPADE_10);
        nextCard(mock_, CardBelote.DIAMOND_10);
        nextCard(mock_, CardBelote.CLUB_10);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),3);
        assertEq(6, hand(csb_, 0).total());
        assertEq(6, hand(csb_, 1).total());
        assertEq(6, hand(csb_, 2).total());
        assertEq(6, hand(csb_, 3).total());
    }
    @Test
    public void p64() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p65() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p66() {
        RulesBelote rules_ = rulesOverBid();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.ALL_TRUMP));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p67() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 80, BidBelote.SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p68() {
        RulesBelote rules_ = rulesDealAll();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p69() {
        RulesBelote rules_ = rulesDealAllOverBid();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 80, BidBelote.ALL_TRUMP));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p70() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p71() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getTeams());
        assertTrue(csb_.window().getDialogTeamsPlayers().getCardDialog().isVisible());
    }
    @Test
    public void p72() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p73() {
        RulesBelote rules_ = rules();
        rules_.getCommon().setNbDeals(2);
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p74() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote(rules_, mock_);
        dealMock(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p75() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote(rules_, mock_);
        dealMock(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p76() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote(rules_, mock_,1);
        dealMock(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p77() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_, 1);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p78() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote(rules_, mock_);
        dealMock(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csb_),StringUtil.join(FacadeCards.defInfos(), "\n"),csb_.window().getFrames().getStreams());
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p79() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csb_),StringUtil.join(FacadeCards.defInfos(), "\n"),csb_.window().getFrames().getStreams());
        tryClick(csb_.getNextDeal());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p80() {
        RulesBelote rules_ = rules();
        rules_.getCommon().setNbDeals(2);
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        tryClick(csb_.getContentPausable().getReplayButton());
        assertEq(5,csb_.partieBelote().getDeal().nombreDeMains());
        assertEq(5,csb_.partieBelote().getDeal().hand((byte) 0).total());
        assertEq(5,csb_.partieBelote().getDeal().hand((byte) 1).total());
        assertEq(5,csb_.partieBelote().getDeal().hand((byte) 2).total());
        assertEq(5,csb_.partieBelote().getDeal().hand((byte) 3).total());
        assertEq(12,csb_.partieBelote().getDeal().hand((byte) 4).total());
    }
    @Test
    public void p81() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p82() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p83() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        CardBelote disc_ = mock_.currentDiscard();
        tryClickCard(component(csb_, disc_));
        tryClickCard(componentDog(csb_, disc_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p84() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(18, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
        assertTrue(tr3_.containsObj(csb_.getValidateDiscard()));
        assertTrue(tr3_.containsObj(csb_.getSlamButton()));
    }
    @Test
    public void p85() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p86() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getValidateDiscard());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p87() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_7)));
        assertTrue(tr3_.containsObj(csb_.getBeloteRebelote()));
    }
    @Test
    public void p88() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p89() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p90() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p91() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(18, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
        assertTrue(tr3_.containsObj(csb_.getValidateDiscard()));
        assertTrue(tr3_.containsObj(csb_.getSlamButton()));
    }
    @Test
    public void p92() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_7)));
        assertTrue(tr3_.containsObj(csb_.getBeloteRebelote()));
    }
    @Test
    public void p93() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT)))));
    }
    @Test
    public void p94() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p95() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        nextSlam(mock_,BoolVal.TRUE);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p96() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        nextSlam(mock_,BoolVal.FALSE);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p97() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p98() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBeloteOtherDisplay(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getContentPausable().getEndDealGame());
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr2_.size());
        assertTrue(tr2_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr2_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr2_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr2_.containsObj(csb_.getContentPausable().getStopButton()));
    }
    @Test
    public void p99() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBeloteOtherDisplay(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getContentPausable().getEndDealGame());
        tryClick(csb_.getContentPausable().getStopButton());
        assertFalse(((MockCustComponent) csb_.window().getPane()).getTreeAccessible().isEmpty());
    }
    @Test
    public void p100() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getSave());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.window().getLoad());
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p101() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
//        tryClick(csb_.getSave());
//        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
//        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
//        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
//        c_.getActionListeners().first().action();
//        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.window().getLoad());
        assertTrue(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
        csb_.window().getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("");
        csb_.window().getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("_");
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileOpenSaveFrame().getMainAction();
        c_.getActionListeners().first().action();
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p102() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getSave());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.getChange());
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p103() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
//        tryClick(csb_.getSave());
//        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
//        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
//        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
//        c_.getActionListeners().first().action();
//        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.getChange());
        assertTrue(csb_.window().getFileSaveFrame().getFrame().isVisible());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(csb_.window().getFileSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p104() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        playMock(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBeloteDir(rules_, mock_);
        assertTrue(csb_.window().getCommonFrame().isVisible());
        new DefPausingCardsAnims().complement(csb_);
        csb_.window().beforeClose();
    }
    @Test
    public void p105() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p106() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_,CardBelote.HEART_7);
        nextCard(mock_,CardBelote.SPADE_7);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(7, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p107() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_,CardBelote.HEART_7);
        nextCard(mock_,CardBelote.SPADE_7);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        assertEq(0,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getSelectedIndex());
        assertEq(-1,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getSelectedIndex());
        assertEq(8, hand(csb_, 0).total());
        assertEq(8, hand(csb_, 1).total());
        assertEq(8, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }
    @Test
    public void p108() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p109() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p110() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        CardBelote disc_ = mock_.currentDiscard();
        tryClickCard(component(csb_, disc_));
        tryClickCard(componentDog(csb_, disc_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p111() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(18, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
        assertTrue(tr3_.containsObj(csb_.getValidateDiscard()));
        assertTrue(tr3_.containsObj(csb_.getSlamButton()));
    }
    @Test
    public void p112() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p113() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getValidateDiscard());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p114() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.getFold());
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_7)));
        assertTrue(tr3_.containsObj(csb_.getBeloteRebelote()));
    }
    @Test
    public void p115() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p116() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p117() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(16, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p118() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(18, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_7)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.DIAMOND_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_KING)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_QUEEN)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_9)));
        assertTrue(tr3_.containsObj(componentDog(csb_,CardBelote.CLUB_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
        assertTrue(tr3_.containsObj(csb_.getValidateDiscard()));
        assertTrue(tr3_.containsObj(csb_.getSlamButton()));
    }
    @Test
    public void p119() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getContentPausable().getMainCardGame());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_7)));
        assertTrue(tr3_.containsObj(csb_.getBeloteRebelote()));
    }
    @Test
    public void p120() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(15, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(80)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
    }
    @Test
    public void p121() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p122() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        nextSlam(mock_,BoolVal.TRUE);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p123() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.SPADE_7);
        nextSlam(mock_,BoolVal.FALSE);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p124() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getConsulting());
        assertFalse(csb_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }

    @Test
    public void p125() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getSave());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.window().getLoad());
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p126() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
//        tryClick(csb_.getSave());
//        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
//        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
//        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
//        c_.getActionListeners().first().action();
//        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.window().getLoad());
        assertTrue(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
        csb_.window().getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("");
        csb_.window().getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("_");
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileOpenSaveFrame().getMainAction();
        c_.getActionListeners().first().action();
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p127() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
        tryClick(csb_.getSave());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.getChange());
        assertFalse(csb_.window().getFileOpenSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p128() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.HEART,80,BidBelote.SUIT));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(mock_,bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextDiscard(mock_, CardBelote.DIAMOND_7);
        nextDiscard(mock_, CardBelote.DIAMOND_8);
        nextDiscard(mock_,CardBelote.SPADE_KING);
        nextDiscard(mock_,CardBelote.SPADE_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_KING);
        nextDiscard(mock_,CardBelote.CLUB_QUEEN);
        nextDiscard(mock_,CardBelote.CLUB_9);
        nextDiscard(mock_,CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = saveBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBidDealAll(csb_, mock_);
        tryAnimate(csb_);
        cliqUniq(csb_,csb_.getSeeDiscard());
        cliqUniq(csb_,csb_.getTakeCardDiscard());
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClickCard(component(csb_,mock_.currentDiscard()));
        tryClick(csb_.getSlamButton());
        tryAnimate(csb_);
//        tryClick(csb_.getSave());
//        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
//        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
//        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
//        c_.getActionListeners().first().action();
//        assertTrue(csb_.window().isPartieSauvegardee());
        tryClick(csb_.getChange());
        assertTrue(csb_.window().getFileSaveFrame().getFrame().isVisible());
        csb_.window().getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        ((MockAbstractAction) GuiBaseUtil.getAction(csb_.window().getFileSaveFrame().getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        MockPlainButton c_ = (MockPlainButton) csb_.window().getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0);
        c_.getActionListeners().first().action();
        assertFalse(csb_.window().getFileSaveFrame().getFrame().isVisible());
    }
    @Test
    public void p129() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(8, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_JACK)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p130() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_,CardBelote.HEART_7);
        nextCard(mock_,CardBelote.SPADE_7);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(7, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_9)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_10)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.CLUB_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.SPADE_1)));
    }
    @Test
    public void p131() {
        RulesBelote rules_ = rulesThreePlayers2();
        DealBelote deal_ = dealThreePlayers();
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_,CardBelote.HEART_7);
        nextCard(mock_,CardBelote.SPADE_7);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterCartesUtilisateur();
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        gb_.ajouterChelemUtilisateur();
        gb_.validateDiscard();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getNextTrick());
        tryClick(csb_.window().getTricksHands());
        assertEq(0,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getSelectedIndex());
        assertEq(-1,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getSelectedIndex());
        assertEq(8, hand(csb_, 0).total());
        assertEq(8, hand(csb_, 1).total());
        assertEq(8, hand(csb_, 2).total());
        assertEq(8, hand(csb_, 3).total());
    }

    @Test
    public void p132() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_,csb_.getContentPausable().getMainCardGame());
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p133() {
        RulesBelote rules_ = rulesThreePlayers_24();
        DealBelote deal_ = dealThreePlayers24Classic();
        MockGameBelote mock_ = new MockGameBelote();
        playMock24Classic(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote24(rules_, mock_, new BeloteSampleFirstDeal24());
        dealMock24Classic(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p134() {
        RulesBelote rules_ = rulesThreePlayers2_24();
        DealBelote deal_ = dealThreePlayers24Coinche();
        MockGameBelote mock_ = new MockGameBelote();
        playMock24Coinche(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote24(rules_, mock_, new BeloteSampleFirstDealAll24());
        dealMock24Coinche(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p135() {
        RulesBelote rules_ = rulesThreePlayers_24();
        DealBelote deal_ = dealThreePlayers24Classic();
        MockGameBelote mock_ = new MockGameBelote();
        playMock24Classic(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote24(rules_, mock_, 1, new BeloteSampleFirstDeal24());
        dealMock24Classic(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p136() {
        RulesBelote rules_ = rulesThreePlayers2_24();
        DealBelote deal_ = dealThreePlayers24Coinche();
        MockGameBelote mock_ = new MockGameBelote();
        playMock24Coinche(mock_);
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = modifyBelote24(rules_, mock_, 1, new BeloteSampleFirstDealAll24());
        dealMock24Coinche(mock_, csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p137() {
        RulesBelote rules_ = rulesThreePlayers_24();
        DealBelote deal_ = dealThreePlayers24Classic();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.SUIT));
        gb_.completerDonne();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        gb_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        gb_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        gb_.ajouterDixDeDerPliEnCours();
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay24(gb_, mock_,0,gb_.getRegles().getDealing());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csb_),StringUtil.join(FacadeCards.defInfos(), "\n"),csb_.window().getFrames().getStreams());
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p138() {
        RulesBelote rules_ = rulesThreePlayers2_24();
        DealBelote deal_ = dealThreePlayers24Coinche();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,162,BidBelote.SUIT));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.completerDonne();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        gb_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        gb_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        gb_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        gb_.ajouterDixDeDerPliEnCours();
        mock_.getStacks().add(new DealBelote(deal_));
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay24(gb_, mock_,0,gb_.getRegles().getDealing());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csb_.getPanelTricksHandsBelote().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(csb_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(csb_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csb_),StringUtil.join(FacadeCards.defInfos(), "\n"),csb_.window().getFrames().getStreams());
        tryClick(csb_.getNextDeal());
        assertEq(4,csb_.partieBelote().getDeal().nombreDeMains());
    }
    @Test
    public void p139() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        gb_.ecarter(mock_);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(5, tr3_.size());
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_KING)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_QUEEN)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_8)));
        assertTrue(tr3_.containsObj(component(csb_,CardBelote.HEART_7)));
        assertTrue(tr3_.containsObj(csb_.getBeloteRebelote()));
    }
    @Test
    public void p140() {
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        assertTrue(containsOnly(csb_).containsObj(csb_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p141() {
        RulesBelote rules_ = rulesTwoPlayers();
        DealBelote deal_ = dealTwoPlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay24(gb_, mock_,0,gb_.getRegles().getDealing());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(2,csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD)))));
        assertTrue(tr_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT)))));

    }
    @Test
    public void p142() {
        RulesBelote rules_ = rulesTwoPlayers2();
        DealBelote deal_ = dealTwoPlayers();
        MockGameBelote mock_ = new MockGameBelote();
        GameBelote gb_ = new GameBelote(GameType.RANDOM,deal_,rules_);
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay24(gb_, mock_,0,gb_.getRegles().getDealing());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(15, tr_.size());
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(80)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(90)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(100)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(110)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(120)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(130)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(140)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(150)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(160)).getButton()));
        assertTrue(tr_.containsObj(csb_.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(162)).getButton()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.HEART, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.SPADE, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.DIAMOND, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getBidsButtons().get(ContainerBelote.index(csb_.getBids(), bidSuit(Suit.CLUB, 0, BidBelote.SUIT))).getPaintableLabel()));
        assertTrue(tr_.containsObj(csb_.getFold()));
    }
    private void dealMock(MockGameBelote _mock, ContainerSingleBelote _csb) {
        tryAnimate(_csb);
        tryClickBid(_csb, _mock);
        tryAnimate(_csb);
        tryClickNextPhase(_csb,_csb.getContentPausable().getMainCardGame());
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        _csb.getBeloteRebelote().setSelected(true);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        _csb.getBeloteRebelote().setSelected(true);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
    }
    private void dealMock24Classic(MockGameBelote _mock, ContainerSingleBelote _csb) {
        tryClickBid(_csb, _mock);
        tryAnimate(_csb);
        tryClickNextPhase(_csb,_csb.getContentPausable().getMainCardGame());
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
    }
    private void dealMock24Coinche(MockGameBelote _mock, ContainerSingleBelote _csb) {
        tryAnimate(_csb);
        tryClickBidDealAll(_csb, _mock);
        tryAnimate(_csb);
        tryClickNextPhase(_csb,_csb.getContentPausable().getMainCardGame());
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
        tryClickCard(_csb, _mock);
        tryAnimate(_csb);
    }
    private void playMock(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.CLUB_10);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_8);
        nextCard(_mock, CardBelote.SPADE_8);
        nextCard(_mock, CardBelote.DIAMOND_8);
        nextCard(_mock, CardBelote.CLUB_8);
        nextCard(_mock, CardBelote.HEART_7);
        nextCard(_mock, CardBelote.SPADE_7);
        nextCard(_mock, CardBelote.DIAMOND_7);
        nextCard(_mock, CardBelote.CLUB_7);
    }

    private void playMock24Classic(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.CLUB_10);
        nextCard(_mock, CardBelote.DIAMOND_KING);
    }

    private void playMock24Coinche(MockGameBelote _mock) {
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.HEART,162,BidBelote.SUIT));
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextBid(_mock, bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        nextCard(_mock, CardBelote.HEART_JACK);
        nextCard(_mock, CardBelote.HEART_QUEEN);
        nextCard(_mock, CardBelote.CLUB_QUEEN);
        nextCard(_mock, CardBelote.HEART_9);
        nextCard(_mock, CardBelote.HEART_KING);
        nextCard(_mock, CardBelote.CLUB_KING);
        nextCard(_mock, CardBelote.HEART_1);
        nextCard(_mock, CardBelote.CLUB_9);
        nextCard(_mock, CardBelote.DIAMOND_JACK);
        nextCard(_mock, CardBelote.HEART_10);
        nextCard(_mock, CardBelote.SPADE_9);
        nextCard(_mock, CardBelote.SPADE_JACK);
        nextCard(_mock, CardBelote.DIAMOND_1);
        nextCard(_mock, CardBelote.DIAMOND_9);
        nextCard(_mock, CardBelote.DIAMOND_QUEEN);
        nextCard(_mock, CardBelote.DIAMOND_10);
        nextCard(_mock, CardBelote.SPADE_KING);
        nextCard(_mock, CardBelote.DIAMOND_KING);
        nextCard(_mock, CardBelote.SPADE_1);
        nextCard(_mock, CardBelote.SPADE_10);
        nextCard(_mock, CardBelote.CLUB_JACK);
        nextCard(_mock, CardBelote.CLUB_1);
        nextCard(_mock, CardBelote.SPADE_QUEEN);
        nextCard(_mock, CardBelote.CLUB_10);
    }

    private void fullPlayedDeal(GameBelote _gb) {
        _gb.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        _gb.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        _gb.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        _gb.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        _gb.completerDonne();
        play(_gb, CardBelote.SPADE_JACK);
        play(_gb, CardBelote.DIAMOND_JACK);
        play(_gb, CardBelote.CLUB_JACK);
        play(_gb, CardBelote.HEART_JACK);
        _gb.ajouterDixDeDerPliEnCours();
        play(_gb, CardBelote.HEART_9);
        play(_gb, CardBelote.SPADE_9);
        play(_gb, CardBelote.DIAMOND_9);
        play(_gb, CardBelote.CLUB_9);
        _gb.ajouterDixDeDerPliEnCours();
        play(_gb, CardBelote.HEART_1);
        play(_gb, CardBelote.SPADE_1);
        play(_gb, CardBelote.DIAMOND_1);
        play(_gb, CardBelote.CLUB_1);
        _gb.ajouterDixDeDerPliEnCours();
        play(_gb, CardBelote.HEART_10);
        play(_gb, CardBelote.SPADE_10);
        play(_gb, CardBelote.DIAMOND_10);
        play(_gb, CardBelote.CLUB_10);
        _gb.ajouterDixDeDerPliEnCours();
        _gb.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        play(_gb, CardBelote.HEART_KING);
        play(_gb, CardBelote.SPADE_KING);
        play(_gb, CardBelote.DIAMOND_KING);
        play(_gb, CardBelote.CLUB_KING);
        _gb.ajouterDixDeDerPliEnCours();
        _gb.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        play(_gb, CardBelote.HEART_QUEEN);
        play(_gb, CardBelote.SPADE_QUEEN);
        play(_gb, CardBelote.DIAMOND_QUEEN);
        play(_gb, CardBelote.CLUB_QUEEN);
        _gb.ajouterDixDeDerPliEnCours();
        play(_gb, CardBelote.HEART_8);
        play(_gb, CardBelote.SPADE_8);
        play(_gb, CardBelote.DIAMOND_8);
        play(_gb, CardBelote.CLUB_8);
        _gb.ajouterDixDeDerPliEnCours();
        play(_gb, CardBelote.HEART_7);
        play(_gb, CardBelote.SPADE_7);
        play(_gb, CardBelote.DIAMOND_7);
        play(_gb, CardBelote.CLUB_7);
        _gb.ajouterDixDeDerPliEnCours();
    }

    private HandBelote hand(ContainerSingleBelote _csb, int _i) {
        return _csb.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTricksHands().getDistribution().hand((byte) _i);
    }

    private void play(GameBelote _gb, CardBelote _card) {
        byte nbPlayers_ = _gb.getNombreDeJoueurs();
        _gb.ajouterUneCarteDansPliEnCoursJoue(_card);
    }

    private CustList<HandBelote> hand(HandBelote _h1, HandBelote _h2, HandBelote _h3,HandBelote _h4) {
        CustList<HandBelote> l_ = new CustList<HandBelote>();
        l_.add(_h1);
        l_.add(_h2);
        l_.add(_h3);
        l_.add(_h4);
        return l_;
    }
    private HandBelote create(CardBelote... _cb) {
        return HandBelote.create(_cb);
    }

    private void tryClickNextPhase(ContainerSingleBelote _csb, AbsCustComponent _button) {
        cliqUniq(_csb, _button);
        tryAnimate(_csb);
    }

    private void cliqUniq(ContainerSingleBelote _csb, AbsCustComponent _button) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        assertTrue(tr_.containsObj(_button));
        tryClick((AbsButton)tr_.get(0));
    }

    private IdList<AbsCustComponent> containsOnly(ContainerSingleBelote _csb) {
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr4_.size());
        return tr4_;
    }

    private static void tryClickBid(ContainerSingleBelote _csb, MockGameBelote _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(ContainerBelote.index(_csb.getBids(), _mock.currentBid())));
    }

    private static void tryClickBidDealAll(ContainerSingleBelote _csb, MockGameBelote _mock) {
        BidBeloteSuit bid_ = _mock.currentBid();
        tryClick(_csb.getPointsButtons().get(RulesBelote.getPoints().indexOfNb(bid_.getPoints())).getButton());
        BidBeloteSuit cp_ = new BidBeloteSuit();
        cp_.setBid(bid_.getBid());
        cp_.setSuit(bid_.getSuit());
        CustList<AbsMouseListenerIntRel> rel_ = _csb.getBidsButtons().get(ContainerBelote.index(_csb.getBids(), cp_)).getPaintableLabel().getMouseListenersRel();
        assertEq(1,rel_.size());
        rel_.get(0).mouseReleased(null,null,null);
        tryClick(_csb.getBidOk());
    }

    private static AbsCustComponent componentDog(ContainerSingleBelote _compo, CardBelote _cb) {
//        HandBelote h_ = new HandBelote();
//        h_.ajouterCartes(_compo.partieBelote().getPliEnCours().getCards());
//        h_.trier(_compo.getDisplayingBelote().getDisplaying().getSuits(), _compo.getDisplayingBelote().getDisplaying().isDecreasing(), _compo.partieBelote().getBid());
//        return _compo.tapisBelote().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
        return _compo.tapisBelote().getCenterDeck().getComponent(_compo.partieBelote().getPliEnCours().getCards().getCards().indexOfObj(_cb));
    }
    private static void tryClickCard(ContainerSingleBelote _compo, MockGameBelote _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }
    private static AbsCustComponent component(ContainerSingleBelote _compo, CardBelote _cb) {
        return _compo.getPanelHand().getComponent(_compo.userHand().getCards().indexOfObj(_cb));
    }
    private ContainerSingleBelote editBelote(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBelote(_mock);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSingleBelote saveBelote(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBeloteSave(_mock);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csb_;
    }
    private ContainerSingleBelote editBeloteOtherDisplay(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private GameBelote edited(DealBelote _deal, RulesBelote _rules) {
        GameBelote g_ = new GameBelote(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }

    private ContainerSingleBelote loadBelote(GameBelote _game, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBelote(_mock);
        return containerGame(_game, wc_);
    }

    private ContainerSingleBelote loadBeloteOtherDisplay(GameBelote _game, MockGameBelote _mock) {
        return loadBeloteOtherDisplay(_game,_mock,0);
    }

    private ContainerSingleBelote loadBeloteOtherDisplay(GameBelote _game, MockGameBelote _mock, int _i) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock,_i);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        return containerGame(_game, wc_);
    }

    private ContainerSingleBelote loadBeloteOtherDisplay24(GameBelote _game, MockGameBelote _mock, int _i, DealingBelote _dealing) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock,_i);
        wc_.getCore().getFacadeCards().getReglesBelote().setDealing(_dealing);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        return containerGame(_game, wc_);
    }

    private ContainerSingleBelote containerGame(GameBelote _game, WindowCards _wc) {
        CheckerGameBeloteWithRules.check(_game, mesCheck());
        assertTrue(_game.getError().isEmpty());
        Games games_ = new Games();
        games_.jouerBelote(_game);
        _wc.tryToLoadDeal("_",games_);
        return (ContainerSingleBelote) _wc.getCore().getContainerGame();
    }

    private ContainerSingleBelote modifyBelote(RulesBelote _rules, MockGameBelote _mock) {
        return modifyBelote(_rules,_mock,0);
    }
    private ContainerSingleBelote modifyBelote(RulesBelote _rules, MockGameBelote _mock, int _i) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock,_i);
        wc_.getCore().getFacadeCards().setReglesBelote(_rules);
        wc_.getCore().setFirstDealBelote(new BeloteSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.BELOTE));
        ContainerSingleBelote csb_ = (ContainerSingleBelote) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSingleBelote modifyBelote24(RulesBelote _rules, MockGameBelote _mock, IntFirstDealBelote _mo) {
        return modifyBelote24(_rules,_mock,0, _mo);
    }
    private ContainerSingleBelote modifyBelote24(RulesBelote _rules, MockGameBelote _mock, int _i, IntFirstDealBelote _mo) {
        WindowCards wc_ = frameSingleBeloteWithEnd24(_mock,_i);
        wc_.getCore().getFacadeCards().setReglesBelote(_rules);
        wc_.getCore().setFirstDealBelote(_mo);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.BELOTE));
        ContainerSingleBelote csb_ = (ContainerSingleBelote) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csb_;
    }
    private ContainerSingleBelote modifyBeloteDir(RulesBelote _rules, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBeloteWithEndModif(_mock,0);
        wc_.getCore().getFacadeCards().setReglesBelote(_rules);
        wc_.getCore().setFirstDealBelote(new BeloteSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        wc_.getCommonFrame().setVisible(true);
        ContainerSingleBelote csb_ = (ContainerSingleBelote) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private static StringMap<String> mesCheck(){
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(CheckerGameBeloteWithRules.BAD_PLAYING,CheckerGameBeloteWithRules.BAD_PLAYING);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_DECLARING,CheckerGameBeloteWithRules.BAD_DECLARING);
        m_.addEntry(CheckerGameBeloteWithRules.BIDDING_TOO_MUCH_LOW,CheckerGameBeloteWithRules.BIDDING_TOO_MUCH_LOW);
        m_.addEntry(CheckerGameBeloteWithRules.BIDDING_LOWER,CheckerGameBeloteWithRules.BIDDING_LOWER);
        m_.addEntry(CheckerGameBeloteWithRules.TOO_MUCH_BIDS,CheckerGameBeloteWithRules.TOO_MUCH_BIDS);
        m_.addEntry(CheckerGameBeloteWithRules.INVALID_BID,CheckerGameBeloteWithRules.INVALID_BID);
        m_.addEntry(CheckerGameBeloteWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK,CheckerGameBeloteWithRules.THERE_SHOULD_NOT_BE_ANY_TRICK);
        m_.addEntry(CheckerGameBeloteWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE,CheckerGameBeloteWithRules.ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_COUNT_FOR_HANDS,CheckerGameBeloteWithRules.BAD_COUNT_FOR_HANDS);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_REP_FOR_HANDS,CheckerGameBeloteWithRules.BAD_REP_FOR_HANDS);
        m_.addEntry(CheckerGameBeloteWithRules.TRICK_WITH_BAD_COUNT,CheckerGameBeloteWithRules.TRICK_WITH_BAD_COUNT);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_COUNT_FOR_REMAINING_CARDS,CheckerGameBeloteWithRules.BAD_COUNT_FOR_REMAINING_CARDS);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_COUNT_FOR_DEAL,CheckerGameBeloteWithRules.BAD_COUNT_FOR_DEAL);
        m_.addEntry(CheckerGameBeloteWithRules.BAD_CARD,CheckerGameBeloteWithRules.BAD_CARD);
        return m_;
    }
    private void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
        _m.getBids().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }

    private void nextSlam(MockGameBelote _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextDiscard(MockGameBelote _m, CardBelote _bid) {
        _m.getDiscard().add(_bid);
    }
    private void nextDiscardIa(MockGameBelote _m, CardBelote... _bid) {
        _m.getDiscardIa().add(create(_bid));
    }

    private void nextCard(MockGameBelote _m, CardBelote _bid) {
        _m.getCards().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }

    private RulesBelote rules() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesBelote rulesThreePlayers() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }


    private RulesBelote rulesThreePlayers2() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesBelote rulesThreePlayers_24() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }


    private RulesBelote rulesThreePlayers2_24() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesBelote rulesTwoPlayers() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_1);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }


    private RulesBelote rulesTwoPlayers2() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_1);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
    private RulesBelote rulesDealAll() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        return rules_;
    }

    private RulesBelote rulesDealAllOverBid() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        return rules_;
    }
    private RulesBelote rulesOverBid() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        return rules_;
    }

    private RulesBelote rulesDeclare() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedDeclares().put(DeclaresBelote.HUNDRED,BoolVal.TRUE);
        return rules_;
    }

    private DealBelote dealThreePlayers24Classic() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 2);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK));
        db_.getDeal().add(create(CardBelote.HEART_1,
                CardBelote.CLUB_1,CardBelote.DIAMOND_10,CardBelote.CLUB_10,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote dealThreePlayers24Coinche() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,
                CardBelote.HEART_1,CardBelote.CLUB_1,CardBelote.DIAMOND_10));
        db_.getDeal().add(create(CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_10));
        db_.getDeal().add(create(
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote dealTwoPlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,
                CardBelote.DIAMOND_9,CardBelote.SPADE_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_9,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK));
        db_.getDeal().add(create(CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.HEART_1,
                CardBelote.CLUB_1,CardBelote.DIAMOND_10,CardBelote.CLUB_10,
                CardBelote.CLUB_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private static DealBelote dealStdClassic(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        firstHands(hands_);
        HandBelote hand_;
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
    private static DealBelote dealStdClassicNext(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        firstHands(hands_);
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        return new DealBelote(hands_, (byte) _dealer);
    }
    private static void firstHands(CustList<HandBelote> _hands) {
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        _hands.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        _hands.add(hand_);
    }

    private static DealBelote deal1Classic(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_1);
        hands_.add(hand_);
        return new DealBelote(hands_, (byte)_dealer);
    }
    private static DealBelote deal2Classic(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_8);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
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
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hands_.add(hand_);
        return new DealBelote(hands_, (byte) _dealer);
    }
    private static DealBelote dealStdAll(int _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.HEART_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
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

    private DealBelote dealThreePlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private DealBelote dealThreePlayersIa() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }
}
