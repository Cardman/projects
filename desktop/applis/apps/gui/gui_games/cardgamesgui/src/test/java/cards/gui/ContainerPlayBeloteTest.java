package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.facade.Games;
import cards.gui.containers.*;
import code.gui.*;
import code.gui.events.AbsMouseListenerIntRel;
import code.mock.*;
import code.util.*;
import code.util.core.BoolVal;
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(0,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        assertEq(1, csb_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr2_.containsObj(csb_.getPanneauBoutonsJeu().getComponent(0)));
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
        tryClickNextPhase(csb_);
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClick((AbsButton) csb_.getPanneauBoutonsJeu().getComponent(0));
        tryAnimate(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(2,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(1,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        assertEq(2,csb_.partieBelote().getAnnoncesBeloteRebelote((byte) 0).total());
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClick((AbsButton) csb_.getPanneauBoutonsJeu().getComponent(0));
        tryAnimate(csb_);
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
        tryClickNextPhase(csb_);
        csb_.getBeloteDeclare().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClick((AbsButton) csb_.getPanneauBoutonsJeu().getComponent(0));
        tryAnimate(csb_);
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
        tryClickNextPhase(csb_);
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        tryClick((AbsButton) tr_.get(0));
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        tryClick((AbsButton) tr_.get(0));
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p31() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_JACK);
        nextCard(mock_, CardBelote.DIAMOND_JACK);
        nextCard(mock_, CardBelote.CLUB_JACK);
        nextCard(mock_, CardBelote.HEART_JACK);
        nextCard(mock_, CardBelote.HEART_9);
        nextCard(mock_, CardBelote.SPADE_9);
        nextCard(mock_, CardBelote.DIAMOND_9);
        nextCard(mock_, CardBelote.CLUB_9);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.SPADE_10);
        nextCard(mock_, CardBelote.DIAMOND_10);
        nextCard(mock_, CardBelote.CLUB_10);
        nextCard(mock_, CardBelote.HEART_KING);
        nextCard(mock_, CardBelote.SPADE_KING);
        nextCard(mock_, CardBelote.DIAMOND_KING);
        nextCard(mock_, CardBelote.CLUB_KING);
        nextCard(mock_, CardBelote.HEART_QUEEN);
        nextCard(mock_, CardBelote.SPADE_QUEEN);
        nextCard(mock_, CardBelote.DIAMOND_QUEEN);
        nextCard(mock_, CardBelote.CLUB_QUEEN);
        nextCard(mock_, CardBelote.HEART_8);
        nextCard(mock_, CardBelote.SPADE_8);
        nextCard(mock_, CardBelote.DIAMOND_8);
        nextCard(mock_, CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_7);
        nextCard(mock_, CardBelote.SPADE_7);
        nextCard(mock_, CardBelote.DIAMOND_7);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p32() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        MockGameBelote mock_ = new MockGameBelote();
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        nextBid(mock_, bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        nextCard(mock_, CardBelote.SPADE_JACK);
        nextCard(mock_, CardBelote.DIAMOND_JACK);
        nextCard(mock_, CardBelote.CLUB_JACK);
        nextCard(mock_, CardBelote.HEART_JACK);
        nextCard(mock_, CardBelote.HEART_9);
        nextCard(mock_, CardBelote.SPADE_9);
        nextCard(mock_, CardBelote.DIAMOND_9);
        nextCard(mock_, CardBelote.CLUB_9);
        nextCard(mock_, CardBelote.HEART_1);
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.SPADE_10);
        nextCard(mock_, CardBelote.DIAMOND_10);
        nextCard(mock_, CardBelote.CLUB_10);
        nextCard(mock_, CardBelote.HEART_KING);
        nextCard(mock_, CardBelote.SPADE_KING);
        nextCard(mock_, CardBelote.DIAMOND_KING);
        nextCard(mock_, CardBelote.CLUB_KING);
        nextCard(mock_, CardBelote.HEART_QUEEN);
        nextCard(mock_, CardBelote.SPADE_QUEEN);
        nextCard(mock_, CardBelote.DIAMOND_QUEEN);
        nextCard(mock_, CardBelote.CLUB_QUEEN);
        nextCard(mock_, CardBelote.HEART_8);
        nextCard(mock_, CardBelote.SPADE_8);
        nextCard(mock_, CardBelote.DIAMOND_8);
        nextCard(mock_, CardBelote.CLUB_8);
        nextCard(mock_, CardBelote.HEART_7);
        nextCard(mock_, CardBelote.SPADE_7);
        nextCard(mock_, CardBelote.DIAMOND_7);
        nextCard(mock_, CardBelote.CLUB_7);
        ContainerSingleBelote csb_ = editBeloteOtherDisplay(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        csb_.getBeloteRebelote().setSelected(true);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getHelpGame());
        assertTrue(csb_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p37() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        assertEq(1,csb_.getPanneauBoutonsJeu().getComponentCount());
    }
    @Test
    public void p42() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        assertEq(1,csb_.getPanneauBoutonsJeu().getComponentCount());
    }
    @Test
    public void p43() {
        RulesBelote rules_ = rules();
        DealBelote deal_ = dealStdClassic(0);
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        nextCard(mock_, CardBelote.SPADE_1);
        nextCard(mock_, CardBelote.DIAMOND_1);
        nextCard(mock_, CardBelote.CLUB_1);
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        tryClickNextPhase(csb_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        MockGameBelote mock_ = new MockGameBelote();
        ContainerSingleBelote csb_ = loadBelote(gb_, mock_);
        tryClickNextPhase(csb_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        tryClickNextPhase(csb_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
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
        GameBelote gb_ = new GameBelote(GameType.EDIT,deal_,rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.completerDonne();
        play(gb_, CardBelote.SPADE_JACK);
        play(gb_, CardBelote.DIAMOND_JACK);
        play(gb_, CardBelote.CLUB_JACK);
        play(gb_, CardBelote.HEART_JACK);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_9);
        play(gb_, CardBelote.SPADE_9);
        play(gb_, CardBelote.DIAMOND_9);
        play(gb_, CardBelote.CLUB_9);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_1);
        play(gb_, CardBelote.SPADE_1);
        play(gb_, CardBelote.DIAMOND_1);
        play(gb_, CardBelote.CLUB_1);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_10);
        play(gb_, CardBelote.SPADE_10);
        play(gb_, CardBelote.DIAMOND_10);
        play(gb_, CardBelote.CLUB_10);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_KING);
        gb_.setAnnoncesBeloteRebelote((byte) 0,CardBelote.HEART_KING);
        play(gb_, CardBelote.SPADE_KING);
        play(gb_, CardBelote.DIAMOND_KING);
        play(gb_, CardBelote.CLUB_KING);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_QUEEN);
        gb_.setAnnoncesBeloteRebelote((byte) 0,CardBelote.HEART_QUEEN);
        play(gb_, CardBelote.SPADE_QUEEN);
        play(gb_, CardBelote.DIAMOND_QUEEN);
        play(gb_, CardBelote.CLUB_QUEEN);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_8);
        play(gb_, CardBelote.SPADE_8);
        play(gb_, CardBelote.DIAMOND_8);
        play(gb_, CardBelote.CLUB_8);
        gb_.ajouterDixDeDerPliEnCours();
        play(gb_, CardBelote.HEART_7);
        play(gb_, CardBelote.SPADE_7);
        play(gb_, CardBelote.DIAMOND_7);
        play(gb_, CardBelote.CLUB_7);
        gb_.ajouterDixDeDerPliEnCours();
        ContainerSingleBelote csb_ = loadBeloteOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
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
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        assertEq(0,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getSelectedIndex());
        assertEq(0,csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getSelectedIndex());
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
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),0);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),1);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),2);
        assertEq(8, hand(csb_, 0).total());
        assertEq(7, hand(csb_, 1).total());
        assertEq(7, hand(csb_, 2).total());
        assertEq(7, hand(csb_, 3).total());
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getCardNumberTrick().getCombo(),3);
        assertEq(7, hand(csb_, 0).total());
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
        nextCard(mock_, CardBelote.HEART_10);
        nextCard(mock_, CardBelote.SPADE_10);
        nextCard(mock_, CardBelote.DIAMOND_10);
        nextCard(mock_, CardBelote.CLUB_10);
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),2);
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
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClickCard(csb_,mock_);
        tryAnimate(csb_);
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getTricksHands());
        eventsCombo(csb_.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTrickNumber().getCombo(),3);
        assertEq(6, hand(csb_, 0).total());
        assertEq(6, hand(csb_, 1).total());
        assertEq(6, hand(csb_, 2).total());
        assertEq(6, hand(csb_, 3).total());
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
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p64() {
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
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p65() {
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
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p66() {
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
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p67() {
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
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p68() {
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
        assertFalse(csb_.getEvents().getText().isEmpty());
    }
    @Test
    public void p69() {
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
        tryClickNextPhase(csb_);
        tryClick(csb_.window().getConsulting());
        assertFalse(csb_.getEvents().getText().isEmpty());
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
        ContainerSingleBelote csb_ = editBelote(rules_, deal_, mock_);
        tryAnimate(csb_);
        tryClickBid(csb_, mock_);
        tryAnimate(csb_);
        tryClick(csb_.window().getTeams());
        assertTrue(csb_.window().getDialogTeamsPlayers().getCardDialog().isVisible());
    }
    private HandBelote hand(ContainerSingleBelote _csb, int _i) {
        return _csb.window().getDialogTricksBelote().getPanelTricksHandsBelote().getTricksHands().getDistribution().hand((byte) _i);
    }

    private void play(GameBelote _gb, CardBelote _card) {
        byte nbPlayers_ = _gb.getNombreDeJoueurs();
        _gb.ajouterUneCarteDansPliEnCours(_gb.getProgressingTrick().getNextPlayer(nbPlayers_), _card);
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

    private void tryClickNextPhase(ContainerSingleBelote _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
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

    private static void tryClickCard(ContainerSingleBelote _compo, MockGameBelote _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }
    private static AbsCustComponent component(ContainerSingleBelote _compo, CardBelote _cb) {
        return _compo.getPanelHand().getComponent(_compo.userHand().getCards().indexOfObj(_cb));
    }
    private static void tryClickCard(AbsCustComponent _compo) {
        assertEq(1,_compo.getMouseListenersRel().size());
        _compo.getMouseListenersRel().get(0).mouseReleased(null,null,null);
    }
    private ContainerSingleBelote editBelote(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBelote(_mock);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(new GameBelote(GameType.EDIT, _deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private ContainerSingleBelote editBeloteOtherDisplay(RulesBelote _rules, DealBelote _deal, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSingleBelote csb_ = new ContainerSingleBelote(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerBelote(new GameBelote(GameType.EDIT, _deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private ContainerSingleBelote loadBelote(GameBelote _game, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBelote(_mock);
        Games games_ = new Games();
        games_.jouerBelote(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleBelote) wc_.getCore().getContainerGame();
    }

    private ContainerSingleBelote loadBeloteOtherDisplay(GameBelote _game, MockGameBelote _mock) {
        WindowCards wc_ = frameSingleBeloteWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingBelote().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        Games games_ = new Games();
        games_.jouerBelote(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleBelote) wc_.getCore().getContainerGame();
    }
    private void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
        _m.getBids().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }


    private void nextCard(MockGameBelote _m, CardBelote _bid) {
        _m.getCards().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }

    private RulesBelote rules() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesBelote rulesDealAll() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        return rules_;
    }

    private RulesBelote rulesDealAllOverBid() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        return rules_;
    }
    private RulesBelote rulesOverBid() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        return rules_;
    }

    private RulesBelote rulesDeclare() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedDeclares().put(DeclaresBelote.HUNDRED,BoolVal.TRUE);
        return rules_;
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

}
