package cards.gui;

import cards.consts.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.stream.*;
import code.util.*;
import code.util.core.*;
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
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
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getTakeCardDog()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getSeeDog()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(6, tr_.size());
        checkCall(cst_,tr_);
        assertTrue(tr_.containsObj(cst_.getValidateDog()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getSeeDog()));
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        CardTarot ct_ = mock_.currentDiscard();
        tryClickCard(component(cst_, ct_));
        tryClickCard(componentDog(cst_,ct_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        tryClickCard(component(cst_, mock_.currentDiscard()));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.SPADE_4)));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(15, tr3_.size());
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
        assertTrue(tr3_.containsObj(cst_.getValidateDog()));
        assertTrue(tr3_.containsObj(cst_.getSlamButton()));
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
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_3);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.EXCUSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_3);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.EXCUSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
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
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.THREE));
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.TWO));
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_3));
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p61() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        oneTrick(mock_, cst_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr4_.size());
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p62() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextHandful(mock_,Handfuls.THREE,CardTarot.TRUMP_21,CardTarot.TRUMP_19,CardTarot.TRUMP_17,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_10,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_3,CardTarot.TRUMP_1);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(mock_.currentHandful().get(0)));
        tryClickCard(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getNextTrick());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr4_.size());
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p63() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.getAllowedHandfuls().put(Handfuls.THREE,0);
        rules_.getAllowedHandfuls().put(Handfuls.FOUR,0);
        rules_.getMiseres().add(Miseres.SUIT);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        rules_.getMiseres().add(Miseres.POINT);
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_,Miseres.LOW_CARDS,Miseres.POINT);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryToggle(cst_.getSelectedMiseres().getVal(mock_.currentMiseres().get(0)));
        tryToggle(cst_.getSelectedMiseres().getVal(mock_.currentMiseres().get(1)));
        tryClickCard(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getNextTrick());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr4_.size());
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p64() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextCard(mock_, CardTarot.TRUMP_21);
        nextCard(mock_, CardTarot.HEART_4);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
        nextCard(mock_, CardTarot.TRUMP_18);
        nextCard(mock_, CardTarot.EXCUSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr5_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr5_.size());
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p65() {
        RulesTarot rules_ = rules();
        rules_.setAllowPlayCalledSuit(true);
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_, CardTarot.HEART_KNIGHT);
        nextCard(mock_, CardTarot.HEART_5);
        nextCard(mock_, CardTarot.HEART_KING);
        nextCard(mock_, CardTarot.HEART_4);
        nextCard(mock_, CardTarot.HEART_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        oneTrick(mock_, cst_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr_.size());
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
    }
    @Test
    public void p66() {
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p67() {
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
        nextSlam(mock_, BoolVal.FALSE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCall(cst_,mock_);
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p68() {
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
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCall(cst_,mock_);
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(25, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p69() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(6, tr_.size());
        checkCall(cst_,tr_);
        assertTrue(tr_.containsObj(cst_.getValidateDog()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p70() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(6, tr_.size());
        checkCall(cst_,tr_);
        assertTrue(tr_.containsObj(cst_.getValidateDog()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p71() {
        RulesTarot rules_ = rules();
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
        nextSlam(mock_, BoolVal.FALSE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p72() {
        RulesTarot rules_ = rules();
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
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(25, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p73() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p74() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p75() {
        RulesTarot rules_ = rules();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p76() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr2_.size());
        checkCall(cst_,tr2_);
        assertTrue(tr2_.containsObj(cst_.getValidateDog()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p77() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextCard(mock_, CardTarot.TRUMP_21);
        nextCard(mock_, CardTarot.HEART_4);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
        nextCard(mock_, CardTarot.TRUMP_18);
        nextCard(mock_, CardTarot.EXCUSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickCard(cst_,mock_);
        tryAnimate(cst_);
        tryClickCard(cst_,mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr5_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr5_.size());
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr5_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p78() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.SLAM);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr2_.size());
        checkCall(cst_,tr2_);
        assertTrue(tr2_.containsObj(cst_.getValidateDog()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p79() {
        RulesTarot rules_ = rulesThree();
        DealTarot deal_ = deal6(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p80() {
        RulesTarot rules_ = rulesThree();
        DealTarot deal_ = deal6(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p81() {
        RulesTarot rules_ = rulesFour();
        DealTarot deal_ = deal5(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p82() {
        RulesTarot rules_ = rulesFour();
        DealTarot deal_ = deal5(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p83() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        oneLastTrick(mock_, cst_);
        assertFalse(cst_.partieTarot().keepPlayingCurrentGame());
    }
    @Test
    public void p84() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        dealMock(mock_,cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
    }
    @Test
    public void p85() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextHandful(mock_,Handfuls.TWO,CardTarot.TRUMP_6,CardTarot.TRUMP_7,CardTarot.TRUMP_10,CardTarot.TRUMP_13,CardTarot.TRUMP_14,CardTarot.TRUMP_17,CardTarot.TRUMP_19,CardTarot.TRUMP_21);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClickHandful(cst_.getHandfulsRadio().getVal(Handfuls.TWO));
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_1));
        tryClickCard(componentHandful(cst_,CardTarot.TRUMP_3));
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(17, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandfulExc(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandfulExc(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.TWO,cst_.getChoosenHandful());
        tryClickCard(cst_, mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getNextTrick());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr4_.size());
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr4_.containsObj(component(cst_,CardTarot.SPADE_KING)));
    }
    @Test
    public void p86() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = dealWithout(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextSlam(mock_, BoolVal.FALSE);
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
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_8)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_KNIGHT)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_6)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
    }
    @Test
    public void p87() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        mock_.getPossible().addEntry(Suit.HEART, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),create(CardTarot.HEART_KING),create(CardTarot.HEART_QUEEN)));
        mock_.getPossible().addEntry(Suit.SPADE, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        mock_.getPossible().addEntry(Suit.DIAMOND, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        mock_.getPossible().addEntry(Suit.CLUB, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        mock_.getPossible().addEntry(Suit.TRUMP, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        mock_.getPossible().addEntry(Suit.UNDEFINED, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        IdMap<Suit, CustList<HandTarot>> id_ = new IdMap<Suit, CustList<HandTarot>>();
        mock_.getSure().addEntry(Hypothesis.SURE, id_);
        mock_.getSure().addEntry(Hypothesis.POSSIBLE, id_);
        id_.addEntry(Suit.HEART, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),create(CardTarot.HEART_KING),create(CardTarot.HEART_QUEEN)));
        id_.addEntry(Suit.SPADE, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        id_.addEntry(Suit.DIAMOND, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        id_.addEntry(Suit.CLUB, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        id_.addEntry(Suit.TRUMP, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        id_.addEntry(Suit.UNDEFINED, hand(new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot(),new HandTarot()));
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_, mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClick(cst_.window().getHelpGame());
        assertTrue(cst_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p88() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        GameTarot gt_ = edited(deal_, rules_);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
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
    public void p89() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        GameTarot gt_ = edited(deal_, rules_);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
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
    public void p90() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        GameTarot gt_ = edited(deal_, rules_);
        MockGameTarot mock_ = new MockGameTarot();
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
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
    public void p91() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p92() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p93() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p94() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
    }
    @Test
    public void p95() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        MockGameTarot mock_ = new MockGameTarot();
        nextDiscardVarIaCall(mock_, false, CardTarot.HEART_KING, CardTarot.DIAMOND_7, CardTarot.DIAMOND_6, CardTarot.DIAMOND_4);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getSeeDog()));
    }
    @Test
    public void p96() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
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
    public void p97() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
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
    public void p98() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
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
    public void p99() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(11, tr3_.size());
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getValidateDog()));
        assertTrue(tr3_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p100() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getSeeDog()));
    }
    @Test
    public void p101() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getSeeDog()));
    }
    @Test
    public void p102() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        gt_.addCurTrickDiscarded();
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p103() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        gt_.addCurTrickDiscarded();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p104() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.HEART_1);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.CLUB_6);
        gt_.addCurTrickDiscarded();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p105() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_6);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_4);
        gt_.addCurTrickDiscarded();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p106() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gererChienInconnuDirect(gt_);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p107() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_6);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_4);
        gt_.addCurTrickDiscarded();
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p108() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gt_.ajouterCartesUtilisateur();
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_7);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_6);
        gt_.ajouterUneCarteDansPliEnCoursPreneur(CardTarot.DIAMOND_4);
        gt_.addCurTrickDiscarded();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p109() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gererChienInconnuDirect(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p110() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.setCarteAppelee(create(CardTarot.HEART_KING));
        gererChienInconnuDirect(gt_);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p111() {
        RulesTarot rules_ = rules();
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1(0);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getMainCardGame()));
    }
    @Test
    public void p112() {
        RulesTarot rules_ = rules();
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.initPlayWithoutBid();
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p113() {
        RulesTarot rules_ = rules();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p114() {
        RulesTarot rules_ = rules();
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_, CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO, cst_.getChoosenHandful());
    }
    @Test
    public void p115() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        GameTarot gt_ = edited(deal_, rules_);
        fullPlayedDeal(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
    }
    @Test
    public void p116() {
        ContainerSingleTarot cst_ = trickHands();
        assertEq(0,cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getSelectedIndex());
        assertEq(-1,cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getSelectedIndex());
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(12, hand(cst_, 2).total());
        assertEq(12, hand(cst_, 3).total());
        assertEq(12, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p117() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),1);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(12, hand(cst_, 2).total());
        assertEq(12, hand(cst_, 3).total());
        assertEq(12, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p118() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        assertEq(11, hand(cst_, 0).total());
        assertEq(11, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(11, hand(cst_, 4).total());
        assertEq(11, hand(cst_, 5).total());
    }
    @Test
    public void p119() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),3);
        assertEq(10, hand(cst_, 0).total());
        assertEq(10, hand(cst_, 1).total());
        assertEq(10, hand(cst_, 2).total());
        assertEq(10, hand(cst_, 3).total());
        assertEq(10, hand(cst_, 4).total());
        assertEq(10, hand(cst_, 5).total());
    }
    @Test
    public void p120() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),2);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(12, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p121() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),1);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(12, hand(cst_, 3).total());
        assertEq(12, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p122() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),3);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(11, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p123() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),4);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(11, hand(cst_, 4).total());
        assertEq(11, hand(cst_, 5).total());
    }
    @Test
    public void p124() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),5);
        assertEq(11, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(11, hand(cst_, 4).total());
        assertEq(11, hand(cst_, 5).total());
    }
    @Test
    public void p125() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),5);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),0);
        assertEq(12, hand(cst_, 0).total());
        assertEq(12, hand(cst_, 1).total());
        assertEq(12, hand(cst_, 2).total());
        assertEq(12, hand(cst_, 3).total());
        assertEq(12, hand(cst_, 4).total());
        assertEq(12, hand(cst_, 5).total());
    }
    @Test
    public void p126() {
        ContainerSingleTarot cst_ = trickHands();
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTrickNumber().getCombo(),2);
        eventsCombo(cst_.window().getDialogTricksTarot().getPanelTricksHandsTarot().getCardNumberTrick().getCombo(),6);
        assertEq(11, hand(cst_, 0).total());
        assertEq(11, hand(cst_, 1).total());
        assertEq(11, hand(cst_, 2).total());
        assertEq(11, hand(cst_, 3).total());
        assertEq(11, hand(cst_, 4).total());
        assertEq(11, hand(cst_, 5).total());
    }
    @Test
    public void p127() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_, cst_.getContentPausable().getEndDealGame());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
    }
    @Test
    public void p128() {
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
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p129() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p130() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p131() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscardVarIaCall(mock_, false, CardTarot.HEART_KING, CardTarot.HEART_7, CardTarot.HEART_1, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p132() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscardVarIaCall(mock_, true, CardTarot.HEART_KING, CardTarot.HEART_7, CardTarot.HEART_1, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p133() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextCall(mock_, CardTarot.HEART_KING);
        nextDiscardIa(mock_, CardTarot.HEART_7, CardTarot.HEART_1, CardTarot.CLUB_6);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p134() {
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
        nextSlam(mock_,BoolVal.FALSE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p135() {
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
        nextSlam(mock_,BoolVal.TRUE);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p136() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p137() {
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
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardTarot.TRUMP_21);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        tryClick(cst_.getConsulting());
        assertFalse(cst_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
    }
    @Test
    public void p138() {
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
        nextSlam(mock_,BoolVal.TRUE);
        nextCard(mock_, CardTarot.TRUMP_21);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_,mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getSlamButton());
        tryAnimate(cst_);
        tryClick(cst_.window().getTeams());
        assertTrue(cst_.window().getDialogTeamsPlayers().getCardDialog().isVisible());
    }
    @Test
    public void p139() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        GameTarot gb_ = new GameTarot(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p140() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.getCommon().setNbDeals(2);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        GameTarot gb_ = edited(deal_, rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p141() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = modifyTarot(rules_, mock_);
        dealMock(mock_, cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p142() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = modifyTarot(rules_, mock_);
        dealMock(mock_, cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p143() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = modifyTarot(rules_, mock_,1);
        dealMock(mock_, cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p144() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        GameTarot gb_ = new GameTarot(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gb_, mock_, 1);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p145() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = modifyTarot(rules_, mock_);
        dealMock(mock_, cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(cst_),StringUtil.join(FacadeCards.defInfos(), "\n"),cst_.window().getFrames().getStreams());
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p146() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        GameTarot gb_ = new GameTarot(GameType.RANDOM,deal_,rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getTrickNumber().self()));
        assertTrue(tr_.containsObj(cst_.getPanelTricksHandsTarot().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getReplayButton()));
        assertTrue(tr_.containsObj(cst_.getContentPausable().getStopButton()));
        assertTrue(tr_.containsObj(cst_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(cst_),StringUtil.join(FacadeCards.defInfos(), "\n"),cst_.window().getFrames().getStreams());
        tryClick(cst_.getNextDeal());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
    }
    @Test
    public void p147() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.getCommon().setNbDeals(2);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        GameTarot gb_ = edited(deal_, rules_);
        fullPlayedDeal(gb_);
        mock_.getStacks().add(new DealTarot(deal_));
        ContainerSingleTarot cst_ = loadTarotOtherDisplay(gb_, mock_);
        tryClick(cst_.getContentPausable().getReplayButton());
        assertEq(6,cst_.partieTarot().getDeal().nombreDeMains());
        assertEq(14,cst_.partieTarot().getDeal().hand((byte) 0).total());
        assertEq(14,cst_.partieTarot().getDeal().hand((byte) 1).total());
        assertEq(14,cst_.partieTarot().getDeal().hand((byte) 2).total());
        assertEq(14,cst_.partieTarot().getDeal().hand((byte) 3).total());
        assertEq(14,cst_.partieTarot().getDeal().hand((byte) 4).total());
        assertEq(8,cst_.partieTarot().getDeal().hand((byte) 5).total());
    }
    @Test
    public void p148() {
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        ContainerSingleTarot cst_ = training(mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertFalse(tr_.isEmpty());
    }
    @Test
    public void p149() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal7(4);
        MockGameTarot mock_ = new MockGameTarot();
        playMock(mock_);
        ContainerSingleTarot cst_ = editTarotOtherDisplay(rules_, deal_, mock_);
        dealMock(mock_,cst_);
        tryClick(cst_.getContentPausable().getStopButton());
        assertFalse(((MockCustComponent) cst_.window().getPane()).getTreeAccessible().isEmpty());
    }
    @Test
    public void p150() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.GUARD_AGAINST);
        nextCall(mock_, CardTarot.HEART_KING);
        nextSlam(mock_, BoolVal.TRUE);
        nextCard(mock_,CardTarot.TRUMP_2);
        nextCard(mock_,CardTarot.TRUMP_5);
        nextCard(mock_,CardTarot.TRUMP_11);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
        ContainerSingleTarot cst_ = editTarot(rules_, deal_, mock_);
        tryAnimate(cst_);
        tryClickBid(cst_, mock_);
        tryAnimate(cst_);
        tryClickCall(cst_,mock_);
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        tryClick(cst_.window().getHelpGame());
        assertTrue(cst_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p151() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(0);
        GameTarot gt_ = edited(deal_, rules_);
        MockGameTarot mock_ = new MockGameTarot();
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getContentPausable().getEndDealGame()));
    }
    @Test
    public void p152() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p153() {
        RulesTarot rules_ = rulesWithoutCall();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        gt_.gererChienInconnu();
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(21, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p154() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.gererChienInconnu();
        MockGameTarot mock_ = new MockGameTarot();
        nextSlam(mock_,BoolVal.FALSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p155() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.gererChienInconnu();
        gt_.firstLead();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        gt_.ajouterPetitAuBoutPliEnCours();
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(2,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        assertEq(5,cst_.partieTarot().getTricks().get(1).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p156() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.gererChienInconnu();
        gt_.firstLead();
        gt_.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        MockGameTarot mock_ = new MockGameTarot();
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p157() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextSlam(mock_,BoolVal.FALSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        clickUniqButton(cst_, cst_.getContentPausable().getMainCardGame());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p158() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterCartesUtilisateur();
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(13, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_2)));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.HEART_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.DIAMOND_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.SPADE_KING))));
        assertTrue(tr3_.containsObj(cst_.getPanelCallableCards().getComponent(indexCall(cst_,CardTarot.CLUB_KING))));
    }
    @Test
    public void p159() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        intelligenceArtificielleAppel(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p160() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_,CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr2_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p161() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(4);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p162() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        intelligenceArtificielleAppel(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p163() {
        RulesTarot rules_ = rules();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_,CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_,mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(22, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.CLUB_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p164() {
        RulesTarot rules_ = rulesDefinedTeams();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal4(4);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(24, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.HEART_KING)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_QUEEN)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.SPADE_KING)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p165() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.TAKE);
        gt_.ajouterContrat(BidTarot.FOLD);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        assertTrue(containsOnly(cst_).containsObj(cst_.getTakeCardDog()));
    }
    @Test
    public void p166() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(6, tr_.size());
        checkCall(cst_,tr_);
        assertTrue(tr_.containsObj(cst_.getValidateDog()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
    }
    @Test
    public void p167() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getValidateDog());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p168() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p169() {
        RulesTarot rules_ = rules();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getValidateDog());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p170() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getValidateDog());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p171() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p172() {
        RulesTarot rules_ = rules();
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        DealTarot deal_ = deal1(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.SLAM);
        MockGameTarot mock_ = new MockGameTarot();
        nextCall(mock_, CardTarot.HEART_KING);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        checkCallOnly(cst_);
        tryClickCall(cst_, mock_);
        tryClick(cst_.getValidateDog());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p173() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(8,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p174() {
        RulesTarot rules_ = rules();
        rules_.setDiscardAfterCall(false);
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        intelligenceArtificielleAppel(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p175() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal4(4);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p176() {
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(3);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        intelligenceArtificielleAppel(gt_);
        MockGameTarot mock_ = new MockGameTarot();
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(cst_.getContentPausable().getMainCardGame()));
        assertTrue(tr_.containsObj(cst_.getSlamButton()));
        tryClick(cst_.getSlamButton());
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(3,cst_.partieTarot().getTricks().get(0).total());
    }
    @Test
    public void p177() {
        RulesTarot rules_ = rulesWithoutCall();
        DealTarot deal_ = deal2(1);
        GameTarot gt_ = edited(deal_, rules_);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.FOLD);
        gt_.ajouterContrat(BidTarot.GUARD_AGAINST);
        gt_.gererChienInconnu();
        MockGameTarot mock_ = new MockGameTarot();
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_2);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_5);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextCard(mock_, CardTarot.TRUMP_11);
        ContainerSingleTarot cst_ = loadTarot(gt_, mock_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(10, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_21)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_1)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(componentHandful(cst_,CardTarot.TRUMP_4)));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertFalse(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p178() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal8(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_2);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        tryAnimate(cst_);
        assertEq(1,cst_.partieTarot().getTricks().size());
        assertEq(6,cst_.partieTarot().getTricks().get(0).total());
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
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.ONE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.TWO).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.THREE).getButton()));
        assertTrue(tr3_.containsObj(cst_.getHandfulsRadio().getVal(Handfuls.NO).getButton()));
        assertEq(Handfuls.NO,cst_.getChoosenHandful());
    }
    @Test
    public void p179() {
        RulesTarot rules_ = rulesDefinedTeams();
        DealTarot deal_ = deal8(1);
        MockGameTarot mock_ = new MockGameTarot();
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.FOLD);
        nextBid(mock_, BidTarot.TAKE);
        nextBid(mock_, BidTarot.FOLD);
        nextDiscard(mock_, CardTarot.HEART_JACK);
        nextDiscard(mock_, CardTarot.DIAMOND_JACK);
        nextDiscard(mock_, CardTarot.SPADE_4);
        nextDiscard(mock_, CardTarot.CLUB_6);
        nextDiscard(mock_, CardTarot.TRUMP_2);
        nextDiscard(mock_, CardTarot.TRUMP_4);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        CardTarot prev_ = mock_.currentDiscard();
        tryClickCard(component(cst_, prev_));
        CardTarot next_ = mock_.currentDiscard();
        tryClickCard(component(cst_, next_));
        tryClickCard(componentDog(cst_, next_));
        tryClickCard(componentDog(cst_, prev_));
        IdList<AbsCustComponent> tr3_ = ((MockCustComponent) cst_.window().getPane()).getTreeAccessible();
        assertEq(14, tr3_.size());
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_2)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_3)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_4)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_6)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_7)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_10)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_13)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_14)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_17)));
        assertTrue(tr3_.containsObj(component(cst_,CardTarot.TRUMP_19)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.HEART_JACK)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.DIAMOND_JACK)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.SPADE_4)));
        assertTrue(tr3_.containsObj(componentDog(cst_,CardTarot.CLUB_6)));
    }
    private void checkCallOnly(ContainerSingleTarot _cst) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _cst.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        checkCall(_cst, tr_);
    }

    private void checkCall(ContainerSingleTarot _cst, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_cst.getPanelCallableCards().getComponent(indexCall(_cst,CardTarot.HEART_KING))));
        assertTrue(_tr.containsObj(_cst.getPanelCallableCards().getComponent(indexCall(_cst,CardTarot.DIAMOND_KING))));
        assertTrue(_tr.containsObj(_cst.getPanelCallableCards().getComponent(indexCall(_cst,CardTarot.SPADE_KING))));
        assertTrue(_tr.containsObj(_cst.getPanelCallableCards().getComponent(indexCall(_cst,CardTarot.CLUB_KING))));
    }

    private ContainerSingleTarot trickHands() {
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
        nextCard(mock_, CardTarot.TRUMP_17);
        nextCard(mock_, CardTarot.HEART_1);
        nextCard(mock_, CardTarot.TRUMP_21);
        nextCard(mock_, CardTarot.HEART_4);
        nextCard(mock_, CardTarot.TRUMP_8);
        nextCard(mock_, CardTarot.TRUMP_9);
        nextCard(mock_, CardTarot.TRUMP_18);
        nextCard(mock_, CardTarot.EXCUSE);
        nextNoHandful(mock_);
        nextMisere(mock_);
        nextNoHandful(mock_);
        nextMisere(mock_);
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
        clickUniqButton(cst_,cst_.getSeeDog());
        clickUniqButton(cst_,cst_.getTakeCardDog());
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClickCard(component(cst_,mock_.currentDiscard()));
        tryClick(cst_.getValidateDog());
        oneTrick(mock_, cst_);
        oneTrick(mock_, cst_);
        tryAnimate(cst_);
        tryClick(cst_.window().getTricksHands());
        return cst_;
    }

    private void oneTrick(MockGameTarot _mock, ContainerSingleTarot _cst) {
        tryAnimate(_cst);
        tryClickCard(_cst, _mock);
        tryAnimate(_cst);
        clickUniqButton(_cst, _cst.getContentPausable().getNextTrick());
    }

    private void oneQuickTrick(MockGameTarot _mock, ContainerSingleTarot _cst) {
        tryClickCard(_cst, _mock);
        tryAnimate(_cst);
    }
    private void oneLastTrick(MockGameTarot _mock, ContainerSingleTarot _cst) {
        tryAnimate(_cst);
        tryClickCard(_cst, _mock);
        tryAnimate(_cst);
    }

    private void clickUniqButton(ContainerSingleTarot _cst, AbsCustComponent _button) {
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) _cst.window().getPane()).getTreeAccessible();
        assertEq(1, tr4_.size());
        assertTrue(tr4_.containsObj(_button));
        tryClick((AbsButton) tr4_.get(0));
    }

    private IdList<AbsCustComponent> containsOnly(ContainerSingleTarot _cst) {
        IdList<AbsCustComponent> tr4_ = ((MockCustComponent) _cst.window().getPane()).getTreeAccessible();
        assertEq(1, tr4_.size());
        return tr4_;
    }

    private HandTarot hand(ContainerSingleTarot _csb, int _i) {
        return _csb.window().getDialogTricksTarot().getPanelTricksHandsTarot().getTricksHands().getDistribution().hand((byte) _i);
    }

    private void play(GameTarot _gb, CardTarot _card) {
        _gb.ajouterUneCarteDansPliEnCours(_card);
    }


    private CustList<HandTarot> hand(HandTarot _h1, HandTarot _h2, HandTarot _h3, HandTarot _h4, HandTarot _h5, HandTarot _h6) {
        CustList<HandTarot> l_ = new CustList<HandTarot>();
        l_.add(_h1);
        l_.add(_h2);
        l_.add(_h3);
        l_.add(_h4);
        l_.add(_h5);
        l_.add(_h6);
        return l_;
    }
    private HandTarot create(CardTarot... _cb) {
        return HandTarot.create(_cb);
    }

    private static void tryClickBid(ContainerSingleTarot _csb, MockGameTarot _mock) {
        tryClick((AbsButton) _csb.getPanneauBoutonsJeu().getComponent(_csb.getBids().indexOfObj(_mock.currentBid())));
    }
    private static AbsCustComponent fold(ContainerSingleTarot _csb) {
        return _csb.getPanneauBoutonsJeu().getComponent(_csb.getBids().indexOfObj(BidTarot.FOLD));
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
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.getCurrentIncludedTrumps());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.getIncludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
        return _compo.getIncludedTrumpsForHandful().getComponent(_compo.getCurrentIncludedTrumps().getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentHandfulExc(ContainerSingleTarot _compo, CardTarot _cb) {
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.getCurrentExcludedTrumps());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.getExcludedTrumpsForHandful().getComponent(h_.getCards().indexOfObj(_cb));
        return _compo.getExcludedTrumpsForHandful().getComponent(_compo.getCurrentExcludedTrumps().getCards().indexOfObj(_cb));
    }
    private static AbsCustComponent componentDog(ContainerSingleTarot _compo, CardTarot _cb) {
//        HandTarot h_ = new HandTarot();
//        h_.ajouterCartes(_compo.partieTarot().getPliEnCours().getCards());
//        h_.trier(_compo.getDisplayingTarot().getDisplaying().getSuits(), _compo.getDisplayingTarot().getDisplaying().isDecreasing());
//        return _compo.tapisTarot().getCenterDeck().getComponent(h_.getCards().indexOfObj(_cb));
        return _compo.tapisTarot().getCenterDeck().getComponent(_compo.partieTarot().getPliEnCours().getCards().getCards().indexOfObj(_cb));
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
        return containerGame(_game, wc_);
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock) {
        return loadTarotOtherDisplay(_game,_mock,0);
    }

    private ContainerSingleTarot loadTarotOtherDisplay(GameTarot _game, MockGameTarot _mock, int _i) {
        WindowCards wc_ = frameSingleTarotWithEnd(_mock,_i);
        wc_.baseWindow().getFacadeCards().getDisplayingTarot().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        return containerGame(_game, wc_);
    }

    private ContainerSingleTarot containerGame(GameTarot _game, WindowCards _wc) {
        CheckerGameTarotWithRules.check(_game);
        assertTrue(_game.getError().isEmpty());
        Games games_ = new Games();
        games_.jouerTarot(_game);
        _wc.tryToLoadDeal("_",games_);
        return (ContainerSingleTarot) _wc.getCore().getContainerGame();
    }

    private ContainerSingleTarot modifyTarot(RulesTarot _rules, MockGameTarot _mock) {
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
    }

    private ContainerSingleTarot training(MockGameTarot _mock) {
        WindowCards wc_ = frameSingleTarot(_mock);
        tryClick(wc_.getTrainingTarot().firstValue());
        return (ContainerSingleTarot)wc_.getCore().getContainerGame();
    }
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

    private void nextHandful(MockGameTarot _m, Handfuls _h, CardTarot... _ct) {
        _m.getHandfuls().add(new IdList<Handfuls>(_h));
        _m.getHandfulsCard().add(create(_ct));
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
        rules_.setAllowedHandfuls(DialogTarotContent.poigneesAutoriseesMap(DealingTarot.DEAL_1_VS_4.getNombreCartesParJoueur()));
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

    private RulesTarot rulesFour() {
        RulesTarot rules_ = new RulesTarot((byte) 4);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private RulesTarot rulesThree() {
        RulesTarot rules_ = new RulesTarot((byte) 3);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
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

    private static DealTarot deal5(int _dealer) {
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
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
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
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
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

    private static DealTarot deal6(int _dealer) {
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

    private static DealTarot deal7(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
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
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
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
        hand_.ajouter(CardTarot.TRUMP_7);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }

    private static DealTarot deal8(int _dealer) {
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
        hand_.ajouter(CardTarot.TRUMP_2);
        hands_.add(hand_);
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
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.SPADE_QUEEN);
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
    private static DealTarot dealWithout(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
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

    private void fullPlayedDeal(GameTarot _gt) {
        _gt.ajouterContrat(BidTarot.SLAM);
        gererChienInconnuDirect(_gt);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_21);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_3);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.EXCUSE);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_5);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_7);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_20);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_6);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_2);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_9);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_11);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_19);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_13);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_8);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_15);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_3);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_18);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_4);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_12);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_3);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_6);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_17);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_4);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_1);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_5);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_5);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_16);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_4);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_2);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_8);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_6);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_14);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_5);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_2);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_8);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_6);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_10);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_7);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_7);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_8);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_7);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_4);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_7);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_3);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_9);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_9);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KING);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_8);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_9);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_10);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_10);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_QUEEN);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_10);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_5);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_JACK);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_JACK);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KING);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_10);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_9);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_JACK);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_QUEEN);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_QUEEN);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_JACK);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KNIGHT);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.HEART_KNIGHT);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.DIAMOND_KING);
        _gt.ajouterPetitAuBoutPliEnCours();
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.TRUMP_1);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.SPADE_KNIGHT);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KNIGHT);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_QUEEN);
        _gt.ajouterUneCarteDansPliEnCours(CardTarot.CLUB_KING);
        _gt.ajouterPetitAuBoutPliEnCours();
    }

    private void playMock(MockGameTarot _mock) {
        nextBid(_mock, BidTarot.SLAM);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextNoHandful(_mock);
        nextMisere(_mock);
        nextCard(_mock, CardTarot.TRUMP_21);
        nextCard(_mock, CardTarot.TRUMP_3);
        nextCard(_mock, CardTarot.EXCUSE);
        nextCard(_mock, CardTarot.TRUMP_5);
        nextCard(_mock, CardTarot.TRUMP_7);
        nextCard(_mock, CardTarot.TRUMP_20);
        nextCard(_mock, CardTarot.TRUMP_6);
        nextCard(_mock, CardTarot.TRUMP_2);
        nextCard(_mock, CardTarot.TRUMP_9);
        nextCard(_mock, CardTarot.TRUMP_11);
        nextCard(_mock, CardTarot.TRUMP_19);
        nextCard(_mock, CardTarot.TRUMP_13);
        nextCard(_mock, CardTarot.TRUMP_8);
        nextCard(_mock, CardTarot.TRUMP_15);
        nextCard(_mock, CardTarot.CLUB_3);
        nextCard(_mock, CardTarot.TRUMP_18);
        nextCard(_mock, CardTarot.CLUB_4);
        nextCard(_mock, CardTarot.TRUMP_12);
        nextCard(_mock, CardTarot.SPADE_3);
        nextCard(_mock, CardTarot.CLUB_6);
        nextCard(_mock, CardTarot.TRUMP_17);
        nextCard(_mock, CardTarot.SPADE_4);
        nextCard(_mock, CardTarot.SPADE_1);
        nextCard(_mock, CardTarot.DIAMOND_5);
        nextCard(_mock, CardTarot.HEART_5);
        nextCard(_mock, CardTarot.TRUMP_16);
        nextCard(_mock, CardTarot.HEART_4);
        nextCard(_mock, CardTarot.SPADE_2);
        nextCard(_mock, CardTarot.SPADE_8);
        nextCard(_mock, CardTarot.SPADE_6);
        nextCard(_mock, CardTarot.TRUMP_14);
        nextCard(_mock, CardTarot.CLUB_5);
        nextCard(_mock, CardTarot.HEART_2);
        nextCard(_mock, CardTarot.CLUB_8);
        nextCard(_mock, CardTarot.HEART_6);
        nextCard(_mock, CardTarot.TRUMP_10);
        nextCard(_mock, CardTarot.DIAMOND_7);
        nextCard(_mock, CardTarot.CLUB_7);
        nextCard(_mock, CardTarot.DIAMOND_8);
        nextCard(_mock, CardTarot.SPADE_7);
        nextCard(_mock, CardTarot.TRUMP_4);
        nextCard(_mock, CardTarot.HEART_7);
        nextCard(_mock, CardTarot.HEART_3);
        nextCard(_mock, CardTarot.DIAMOND_9);
        nextCard(_mock, CardTarot.SPADE_9);
        nextCard(_mock, CardTarot.HEART_KING);
        nextCard(_mock, CardTarot.HEART_8);
        nextCard(_mock, CardTarot.HEART_9);
        nextCard(_mock, CardTarot.HEART_10);
        nextCard(_mock, CardTarot.DIAMOND_10);
        nextCard(_mock, CardTarot.HEART_QUEEN);
        nextCard(_mock, CardTarot.CLUB_10);
        nextCard(_mock, CardTarot.SPADE_5);
        nextCard(_mock, CardTarot.HEART_JACK);
        nextCard(_mock, CardTarot.DIAMOND_JACK);
        nextCard(_mock, CardTarot.SPADE_KING);
        nextCard(_mock, CardTarot.SPADE_10);
        nextCard(_mock, CardTarot.CLUB_9);
        nextCard(_mock, CardTarot.CLUB_JACK);
        nextCard(_mock, CardTarot.DIAMOND_QUEEN);
        nextCard(_mock, CardTarot.SPADE_QUEEN);
        nextCard(_mock, CardTarot.SPADE_JACK);
        nextCard(_mock, CardTarot.DIAMOND_KNIGHT);
        nextCard(_mock, CardTarot.HEART_KNIGHT);
        nextCard(_mock, CardTarot.DIAMOND_KING);
        nextCard(_mock, CardTarot.TRUMP_1);
        nextCard(_mock, CardTarot.SPADE_KNIGHT);
        nextCard(_mock, CardTarot.CLUB_KNIGHT);
        nextCard(_mock, CardTarot.CLUB_QUEEN);
        nextCard(_mock, CardTarot.CLUB_KING);
    }

    private void dealMock(MockGameTarot _mock, ContainerSingleTarot _cst) {
        tryClickBid(_cst,_mock);
        tryAnimate(_cst);
        clickUniqButton(_cst, _cst.getContentPausable().getMainCardGame());
        tryAnimate(_cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
        oneQuickTrick(_mock, _cst);
    }

    public static void intelligenceArtificielleAppel(GameTarot _gt) {
        _gt.intelligenceArtificielleAppel(new DefGameTarot());
    }
    public static void gererChienInconnuDirect(GameTarot _gt) {
        _gt.gererChienInconnu();
        _gt.firstLead();
    }

    public static void gererChienInconnuChelemDirect(GameTarot _gt) {
        _gt.gererChienInconnu();
        _gt.ajouterChelemUtilisateur();
        _gt.firstLead();
    }
}
