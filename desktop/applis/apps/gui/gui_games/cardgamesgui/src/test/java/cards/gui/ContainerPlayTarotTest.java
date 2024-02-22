package cards.gui;

import cards.consts.*;
import cards.facade.Games;
import cards.gui.containers.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class ContainerPlayTarotTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p2() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertFalse(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p3() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p4() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p5() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p6() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p7() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p8() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p9() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(5,cst_.getPanneauBoutonsJeu().getComponentCount());
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.FOLD))));
        assertFalse(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.TAKE))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_WITHOUT))));
        assertTrue(tr_.containsObj(cst_.getPanneauBoutonsJeu().getComponent(cst_.getBids().indexOfObj(BidTarot.GUARD_AGAINST))));
    }
    @Test
    public void p10() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p11() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p12() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p13() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p14() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p15() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscardIa(mock_, CardTarot.DIAMOND_7, CardTarot.DIAMOND_6, CardTarot.DIAMOND_4);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p16() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextDiscardVarIaCall(mock_, false, CardTarot.HEART_KING, CardTarot.DIAMOND_7, CardTarot.DIAMOND_6, CardTarot.DIAMOND_4);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p17() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p18() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p19() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p20() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        CardTarot ct_ = mock_.currentDiscard();
        tryClickCard(component(cst_, ct_));
        tryClickCard(componentDog(cst_,ct_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p21() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p22() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.SPADE_4);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p23() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.SPADE_4);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        CardTarot ct_ = mock_.currentDiscard();
        tryClickCard(component(cst_, ct_));
        tryClickCard(componentDog(cst_, ct_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p24() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p25() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p26() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        CardTarot ct_ = mock_.currentDiscard();
        tryClickCard(component(cst_, ct_));
        tryClickCard(componentDog(cst_, ct_));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p27() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p28() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.SPADE_4);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p29() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.SPADE_4);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        CardTarot ct_ = mock_.currentDiscard();
        tryClickCard(component(cst_, ct_));
        tryClickCard(componentDog(cst_, ct_));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr2_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr2_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p30() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_7);
        nextDiscard(mock_, CardTarot.HEART_1);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr3_.size());
    }
    @Test
    public void p31() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void p32() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_3)));
    }
    @Test
    public void p33() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p34() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextSlam(mock_, BoolVal.FALSE);
        nextDiscardIa(mock_, CardTarot.HEART_QUEEN, CardTarot.HEART_8, CardTarot.HEART_4,CardTarot.DIAMOND_1,CardTarot.DIAMOND_2,CardTarot.DIAMOND_3,CardTarot.DIAMOND_4,CardTarot.DIAMOND_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p35() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p36() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void p37() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_3)));
    }
    @Test
    public void p38() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal2(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p39() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextSlam(mock_, BoolVal.FALSE);
        nextDiscardIa(mock_, CardTarot.HEART_QUEEN, CardTarot.HEART_8, CardTarot.HEART_4,CardTarot.DIAMOND_1,CardTarot.DIAMOND_2,CardTarot.DIAMOND_3,CardTarot.DIAMOND_4,CardTarot.DIAMOND_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p40() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.setDiscardAfterCall(false);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal2(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p41() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void p42() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_3)));
    }
    @Test
    public void p43() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal3(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p44() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextSlam(mock_, BoolVal.FALSE);
        nextDiscardIa(mock_, CardTarot.DIAMOND_1,CardTarot.DIAMOND_2,CardTarot.DIAMOND_3,CardTarot.DIAMOND_4,CardTarot.DIAMOND_6,CardTarot.DIAMOND_7);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p45() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p46() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void p47() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(9, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_3)));
    }
    @Test
    public void p48() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal3(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p49() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextSlam(mock_, BoolVal.FALSE);
        nextDiscardIa(mock_, CardTarot.DIAMOND_1,CardTarot.DIAMOND_2,CardTarot.DIAMOND_3,CardTarot.DIAMOND_4,CardTarot.DIAMOND_6,CardTarot.DIAMOND_7);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
    }
    @Test
    public void p50() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.setDiscardAfterCall(false);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal3(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p51() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p52() {
        RulesTarot rules_ = rules();
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
    }
    @Test
    public void p53() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p54() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.THREE,cst_.getChoosenHandful());
    }
    @Test
    public void p55() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.TWO));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.TWO,cst_.getChoosenHandful());
    }
    @Test
    public void p56() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.TWO));
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.THREE,cst_.getChoosenHandful());
    }
    @Test
    public void p57() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_3));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandfulExc(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.THREE,cst_.getChoosenHandful());
    }
    @Test
    public void p58() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_3));
        tryClickCard(componentHandfulExc(cst_,CardTarot.TRUMP_3));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.THREE,cst_.getChoosenHandful());
    }
    @Test
    public void p59() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.NO));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p60() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.SPADE_QUEEN);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextCard(mock_, CardTarot.TRUMP_11);
        nextCard(mock_, CardTarot.TRUMP_16);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(1, tr2_.size());
        tryClick((AbsButton) tr2_.get(0));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_3));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandfulExc(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getPaintableLabel()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getPaintableLabel()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    private HandTarot hand(ContainerSingleTarot _csb, int _i) {
        return _csb.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTricksHands().getDistribution().hand((byte) _i);
    }

    private void play(GameTarot _gb, CardTarot _card) {
        _gb.ajouterUneCarteDansPliEnCours(_card);
    }


    private CustList<HandTarot> hand(HandTarot _h1, HandTarot _h2, HandTarot _h3, HandTarot _h4) {
        CustList<HandTarot> l_ = new CustList<HandTarot>();
        l_.add(_h1);
        l_.add(_h2);
        l_.add(_h3);
        l_.add(_h4);
        return l_;
    }
    private HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }

    private void tryClickNextPhase(ContainerSingleTarot _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
    }

    private static void tryClickBid(ContainerSingleTarot _csb, MockGameTarot _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(_csb.getBids().indexOfObj(_mock.currentBid())));
    }


    private static void tryClickCard(ContainerSingleTarot _compo, MockGameTarot _mock) {
        tryClickCard(component(_compo,_mock.currentCard()));
    }

    private static void tryClickCall(ContainerSingleTarot _compo, MockGameTarot _mock) {
        tryClickCard(componentCall(_compo,_mock.currentCall().premiereCarte()));
    }
    private static AbsCustComponent component(ContainerSingleTarot _compo, CardTarot _cb) {
        return _compo.getPanelHand().getComponent(_compo.userHand().getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentHandful(ContainerSingleTarot _compo, CardTarot _cb) {
        HandTarot h_ = new HandTarot();
        h_.ajouterCartes(_compo.getCurrentIncludedTrumps());
        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
        return _compo.getIncludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentHandfulExc(ContainerSingleTarot _compo, CardTarot _cb) {
        HandTarot h_ = new HandTarot();
        h_.ajouterCartes(_compo.getCurrentExcludedTrumps());
        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
        return _compo.getExcludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentDog(ContainerSingleTarot _compo, CardTarot _cb) {
        HandTarot h_ = new HandTarot();
        h_.ajouterCartes(_compo.partieTarot().getPliEnCours().getCards());
        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
        return _compo.tapisTarot().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentCall(ContainerSingleTarot _compo, CardTarot _cb) {
        int index_ = indexCall(_compo, _cb);
        return _compo.getPanelCallableCards().getComponent(index_);
    }

    private static int indexCall(ContainerSingleTarot _compo, CardTarot _cb) {
        GameTarot partie_ = _compo.partieTarot();
        HandTarot callableCards_ = partie_.callableCards();
        return callableCards_.getCards().indexOfObj(_cb);
    }

    private ContainerSingleTarot editTarot(RulesTarot _rules, DealTarot _deal, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarot(_mock);
        ContainerSingleTarot cst_ = new ContainerSingleTarot(wc_);
        wc_.getCore().setContainerGame(cst_);
        cst_.editerTarot(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }

    private ContainerSingleTarot editTarotOtherDisplay(RulesTarot _rules, DealTarot _deal, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSingleTarot cst_ = new ContainerSingleTarot(wc_);
        wc_.getCore().setContainerGame(cst_);
        cst_.editerTarot(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }

    private GameTarot edited(DealTarot _deal, RulesTarot _rules) {
        GameTarot g_ = new GameTarot(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }

    
    private ContainerSingleTarot loadTarot(GameTarot _game, MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarot(_mock);
        Games games_ = new Games();
        games_.jouerTarot(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleTarot) wc_.getCore().getContainerGame();
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock) {
        return loadTarotOtherDisplay(_game,_mock,0);
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock, int _i) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock,_i);
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        Games games_ = new Games();
        games_.jouerTarot(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSingleTarot) wc_.getCore().getContainerGame();
    }

    /*private ContainerSingleTarot modifyTarot(RulesTarot _rules, MockGameTarot _mock) {
        return modifyTarot(_rules,_mock,0);
    }
    private ContainerSingleTarot modifyTarot(RulesTarot _rules, MockGameTarot _mock, int _i) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock,_i);
        wc_.getCore().getFacadeCards().setReglesTarot(_rules);
        wc_.getCore().setFirstDealTarot(new TarotSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.TAROT));
        ContainerSingleTarot cst_ = (ContainerSingleTarot) wc_.getCore().getContainerGame();
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return cst_;
    }*/
    private void nextBid(MockGameTarot _m, BidTarot _bid) {
        _m.getBids().add(_bid);
    }

    private void nextCall(MockGameTarot _m, CardTarot _bid) {
        _m.getCalls().add(create(_bid));
    }

    private void nextSlam(MockGameTarot _m, BoolVal _bid) {
        _m.getSlams().add(_bid);
    }
    private void nextDiscard(MockGameTarot _m, CardTarot _bid) {
        _m.getDiscard().add(_bid);
    }
    private void nextDiscardIa(MockGameTarot _m, CardTarot... _bid) {
        _m.getDiscardIa().add(create(_bid));
    }
    private void nextDiscardVarIa(MockGameTarot _m, boolean _slam, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private void nextDiscardVarIaCall(MockGameTarot _m, boolean _slam, CardTarot _card, CardTarot... _bid) {
        CallDiscard c_ = new CallDiscard();
        c_.setCarteAppelee(create(_card));
        c_.setEcartAFaire(create(_bid));
        c_.setChelem(_slam);
        _m.getDiscardVarIa().add(c_);
    }
    private void nextCard(MockGameTarot _m, CardTarot _bid) {
        _m.getCards().add(_bid);
    }

    private void nextNoHandful(MockGameTarot _m) {
        _m.getHandfuls().add(new IdList<Handfuls>());
        _m.getHandfulsCard().add(new HandTarot());
    }

    private void nextMisere(MockGameTarot _m, Miseres... _mis) {
        _m.getMiseres().add(new IdList<Miseres>(_mis));
    }
    private RulesTarot rules() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesTarot rulesWithoutCall() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingTarot.DEAL_1_VS_4);
        return rules_;
    }

    private RulesTarot rulesDefinedTeams() {
        RulesTarot rules_ = new RulesTarot((byte) 6);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        return rules_;
    }

    private static DealTarot deal1(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

    private static DealTarot deal2(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

    private static DealTarot deal3(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

    private static DealTarot deal4(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

}
