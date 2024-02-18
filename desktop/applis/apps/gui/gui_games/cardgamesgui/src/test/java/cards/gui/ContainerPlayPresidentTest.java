package cards.gui;

import cards.consts.*;
import cards.facade.*;
import cards.gui.containers.*;
import cards.president.*;
import cards.president.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class ContainerPlayPresidentTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p2() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.HEART_4,CardPresident.SPADE_4));
        nextCard(mock_, create(CardPresident.HEART_5,CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p3() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(12, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4,CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p4() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        nextCard(mock_, create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(10, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p5() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        nextCard(mock_, create(CardPresident.DIAMOND_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(11, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p6() {
        RulesPresident r_ = rules();
        r_.setHasToPlay(true);
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(10, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
    }
    @Test
    public void p7() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.HEART_7));
        nextCard(mock_, create(CardPresident.DIAMOND_7));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(2, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p8() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(13, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_4,CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
    }
    @Test
    public void p9() {
        RulesPresident r_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void p10() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getHelpGame());
        assertTrue(csp_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p11() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getHelpGame());
        assertTrue(csp_.window().getHelpGame().isEnabled());
    }
    @Test
    public void p12() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(9,th_.getDistribution().hand((byte) 1).total());
        assertEq(13,th_.getDistribution().hand((byte) 2).total());
        assertEq(13,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p13() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),2);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(9,th_.getDistribution().hand((byte) 1).total());
        assertEq(13,th_.getDistribution().hand((byte) 2).total());
        assertEq(13,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p14() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.HEART_1));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),3);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(8,th_.getDistribution().hand((byte) 1).total());
        assertEq(13,th_.getDistribution().hand((byte) 2).total());
        assertEq(13,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p15() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),1);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(13,th_.getDistribution().hand((byte) 1).total());
        assertEq(13,th_.getDistribution().hand((byte) 2).total());
        assertEq(13,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p16() {
        RulesPresident r_ = rules();
        r_.setPossibleReversing(true);
        DealPresident deal_ = new DealPresident(deal3(),(byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK,CardPresident.HEART_JACK,CardPresident.DIAMOND_JACK));
        nextCard(mock_, create(CardPresident.HEART_1));
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.CLUB_KING));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),3);
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),2);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(8,th_.getDistribution().hand((byte) 1).total());
        assertEq(13,th_.getDistribution().hand((byte) 2).total());
        assertEq(13,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p17() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),2);
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),5);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(11,th_.getDistribution().hand((byte) 1).total());
        assertEq(12,th_.getDistribution().hand((byte) 2).total());
        assertEq(12,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p18() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),5);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(11,th_.getDistribution().hand((byte) 1).total());
        assertEq(12,th_.getDistribution().hand((byte) 2).total());
        assertEq(12,th_.getDistribution().hand((byte) 3).total());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),1);
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),5);
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),0);
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),2);
    }
    @Test
    public void p19() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getCardNumberTrick().getCombo(),3);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(13,th_.getDistribution().hand((byte) 0).total());
        assertEq(10,th_.getDistribution().hand((byte) 1).total());
        assertEq(12,th_.getDistribution().hand((byte) 2).total());
        assertEq(12,th_.getDistribution().hand((byte) 3).total());
    }
    @Test
    public void p20() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create(CardPresident.HEART_2));
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getConsulting());
        assertFalse(csp_.getEvents().getText().isEmpty());
    }
    @Test
    public void p21() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        nextCard(mock_, create());
        nextCard(mock_, create(CardPresident.DIAMOND_2));
        nextCard(mock_, create(CardPresident.SPADE_4));
        nextCard(mock_, create());
        nextCard(mock_, create());
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClick(csp_.window().getConsulting());
        assertFalse(csp_.getEvents().getText().isEmpty());
    }
    @Test
    public void p22() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.CLUB_3,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(15, tr2_.size());
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
    }
    @Test
    public void p23() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.CLUB_3,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(CardPresident.CLUB_3)));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(15, tr2_.size());
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
    }
    @Test
    public void p24() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.CLUB_3,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(CardPresident.CLUB_3)));
        tryClickCard(componentGiven(csp_,create(CardPresident.CLUB_3)));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(15, tr2_.size());
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
    }
    @Test
    public void p25() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(CardPresident.CLUB_4)));
        tryClickCard(componentReceived(csp_,create(CardPresident.DIAMOND_4)));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(16, tr2_.size());
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
        assertTrue(tr2_.containsObj(csp_.getGivingCardsOk()));
    }
    @Test
    public void p26() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(mock_.currentSwitch().carte(0))));
        tryClickCard(componentReceived(csp_,create(mock_.currentSwitch().carte(1))));
        tryClick(csp_.getGivingCardsOk());
        assertEq(13,csp_.userHand().total());
        assertEq(13,csp_.partiePresident().getDeal().hand((byte) 0).total());
        assertEq(13,csp_.partiePresident().getDeal().hand((byte) 1).total());
        assertEq(13,csp_.partiePresident().getDeal().hand((byte) 2).total());
        assertEq(13,csp_.partiePresident().getDeal().hand((byte) 3).total());
    }
    @Test
    public void p27() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        nextCard(mock_,create(CardPresident.HEART_3));
        nextCard(mock_,create(CardPresident.HEART_6));
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(mock_.currentSwitch().carte(0))));
        tryClickCard(componentReceived(csp_,create(mock_.currentSwitch().carte(1))));
        tryClick(csp_.getGivingCardsOk());
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(13, tr2_.size());
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
    }
    @Test
    public void p28() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.CLUB_3,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClick(csp_.window().getConsulting());
        assertFalse(csp_.getEvents().getText().isEmpty());
    }
    @Test
    public void p29() {
        RulesPresident r_ = rules3();
        r_.setPossibleReversing(false);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        r_.getCommon().setNbDeals(2);
        r_.setLooserStartsFirst(true);
        DealPresident deal_ = new DealPresident(deal4(), (byte) 2);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame3(mock_);
        DealPresident dealSec_ = new DealPresident(deal4(), (byte) 1);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.CLUB_3,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal3(deal_, mock_, r_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(0, tr2_.size());
    }
    @Test
    public void p30() {
        RulesPresident r_ = rules3();
        r_.setPossibleReversing(false);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        r_.getCommon().setNbDeals(2);
        r_.setLooserStartsFirst(true);
        DealPresident deal_ = new DealPresident(deal4(), (byte) 2);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame4(mock_);
        DealPresident dealSec_ = new DealPresident(deal4(), (byte) 1);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = oneDeal4(deal_, mock_, r_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(17, tr2_.size());
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_3,CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_3,CardPresident.CLUB_3,CardPresident.SPADE_3))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_4,CardPresident.HEART_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_4,CardPresident.HEART_4,CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_4,CardPresident.HEART_4,CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7,CardPresident.CLUB_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_8,CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_JACK,CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
    }
    @Test
    public void p31() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        mock_.getStacks().add(dealSec_);
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        mock_.getSw().add(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = oneDeal(deal_, mock_, r_);
        tryClickCard(componentReceived(csp_,create(CardPresident.CLUB_4)));
        tryClickCard(componentReceived(csp_,create(CardPresident.DIAMOND_4)));
        tryClickCard(componentReceived(csp_,create(CardPresident.SPADE_7)));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(15, tr2_.size());
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_3))));
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.CLUB_4))));
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.CLUB_4,CardPresident.DIAMOND_4))));
        assertTrue(tr2_.containsObj(componentGiven(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2))));
        assertTrue(tr2_.containsObj(componentReceived(csp_,create(CardPresident.HEART_2,CardPresident.SPADE_2,CardPresident.CLUB_2))));
    }
    private ContainerSinglePresident oneDeal(DealPresident _deal, MockGamePresident _mock, RulesPresident _r) {
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(_r, _deal, _mock);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        tryClick(csp_.getNextDeal());
        return csp_;
    }

    private ContainerSinglePresident oneDeal3(DealPresident _deal, MockGamePresident _mock, RulesPresident _r) {
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(_r, _deal, _mock);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        tryClick(csp_.getNextDeal());
        return csp_;
    }

    private ContainerSinglePresident oneDeal4(DealPresident _deal, MockGamePresident _mock, RulesPresident _r) {
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(_r, _deal, _mock);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClickCard(csp_, _mock);
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        tryClick(csp_.getNoPlay());
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        tryClick(csp_.getNextDeal());
        tryAnimate(csp_);
        return csp_;
    }
    private void mockGame(MockGamePresident _mock) {
        nextCard(_mock, create(CardPresident.SPADE_3));
        nextCard(_mock, create(CardPresident.HEART_3));
        nextCard(_mock, create(CardPresident.DIAMOND_3));
        nextCard(_mock, create(CardPresident.CLUB_3));
        nextCard(_mock, create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        nextCard(_mock, create(CardPresident.SPADE_4,CardPresident.HEART_4));
        nextCard(_mock, create(CardPresident.CLUB_7));
        nextCard(_mock, create(CardPresident.HEART_7));
        nextCard(_mock, create(CardPresident.DIAMOND_7));
        nextCard(_mock, create(CardPresident.SPADE_7));
        nextCard(_mock, create(CardPresident.HEART_8,CardPresident.DIAMOND_8));
        nextCard(_mock, create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        nextCard(_mock, create(CardPresident.SPADE_9));
        nextCard(_mock, create(CardPresident.HEART_9));
        nextCard(_mock, create(CardPresident.DIAMOND_9));
        nextCard(_mock, create(CardPresident.CLUB_9));
        nextCard(_mock, create(CardPresident.SPADE_10));
        nextCard(_mock, create(CardPresident.CLUB_10));
        nextCard(_mock, create(CardPresident.HEART_10));
        nextCard(_mock, create(CardPresident.DIAMOND_10));
        nextCard(_mock, create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        nextCard(_mock, create(CardPresident.HEART_KING));
        nextCard(_mock, create(CardPresident.DIAMOND_KING));
        nextCard(_mock, create(CardPresident.SPADE_KING));
        nextCard(_mock, create(CardPresident.CLUB_KING));
        nextCard(_mock, create(CardPresident.HEART_1));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_1));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_2));
        nextCard(_mock, create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK));
        nextCard(_mock, create(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        nextCard(_mock, create(CardPresident.DIAMOND_2));
        nextCard(_mock, create(CardPresident.SPADE_5,CardPresident.HEART_5));
        nextCard(_mock, create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(_mock, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        nextCard(_mock, create(CardPresident.SPADE_6,CardPresident.CLUB_6));
        nextCard(_mock, create(CardPresident.SPADE_2,CardPresident.CLUB_2));
    }

    private void mockGame3(MockGamePresident _mock) {
        nextCard(_mock, create(CardPresident.CLUB_3,CardPresident.SPADE_3));
        nextCard(_mock, create(CardPresident.SPADE_5,CardPresident.HEART_5));
        nextCard(_mock, create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(_mock, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        nextCard(_mock, create(CardPresident.SPADE_7,CardPresident.CLUB_7));
        nextCard(_mock, create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        nextCard(_mock, create(CardPresident.HEART_KING,CardPresident.DIAMOND_KING));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN,CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        nextCard(_mock, create(CardPresident.CLUB_2,CardPresident.SPADE_2));
        nextCard(_mock, create(CardPresident.SPADE_1,CardPresident.CLUB_1));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_10));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_9));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_7));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_3));
        nextCard(_mock, create(CardPresident.HEART_2));
        nextCard(_mock, create(CardPresident.DIAMOND_1));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_KING));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_JACK));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.CLUB_JACK));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_10));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.CLUB_9));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_8,CardPresident.DIAMOND_8));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_4,CardPresident.HEART_4,CardPresident.CLUB_4,CardPresident.DIAMOND_4));
    }

    private void mockGame4(MockGamePresident _mock) {
        nextCard(_mock, create(CardPresident.CLUB_3,CardPresident.SPADE_3));
        nextCard(_mock, create(CardPresident.SPADE_5,CardPresident.HEART_5));
        nextCard(_mock, create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        nextCard(_mock, create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        nextCard(_mock, create(CardPresident.SPADE_7,CardPresident.CLUB_7));
        nextCard(_mock, create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        nextCard(_mock, create(CardPresident.HEART_KING,CardPresident.DIAMOND_KING));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN,CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        nextCard(_mock, create(CardPresident.CLUB_2,CardPresident.SPADE_2));
        nextCard(_mock, create(CardPresident.SPADE_1,CardPresident.CLUB_1));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_10));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_9));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_7));
        nextCard(_mock, create());
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_3));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_2));
        nextCard(_mock, create(CardPresident.HEART_1));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.CLUB_KING));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_10,CardPresident.CLUB_10));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_9,CardPresident.HEART_9));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_7));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.SPADE_6,CardPresident.CLUB_6));
        nextCard(_mock, create());
        nextCard(_mock, create(CardPresident.HEART_3));
        nextCard(_mock, create());
    }
    private void display(ContainerSinglePresident _csp) {
        _csp.getDisplayingPresident().getDisplaying().setSuits(new IdList<Suit>(Suit.HEART,Suit.SPADE,Suit.DIAMOND,Suit.CLUB));
    }
//    private HandPresident hand(ContainerSinglePresident _csb, int _i) {
//        return _csb.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands().getDistribution().hand((byte) _i);
//    }
//
//    private void play(GamePresident _gb, CardPresident _card) {
//        byte nbPlayers_ = _gb.getNombreDeJoueurs();
//        _gb.ajouterUneCarteDansPliEnCours(_gb.getProgressingTrick().getNextPlayer(nbPlayers_), _card);
//    }

//    private CustList<HandPresident> hand(HandPresident _h1, HandPresident _h2, HandPresident _h3,HandPresident _h4) {
//        CustList<HandPresident> l_ = new CustList<HandPresident>();
//        l_.add(_h1);
//        l_.add(_h2);
//        l_.add(_h3);
//        l_.add(_h4);
//        return l_;
//    }
    private HandPresident create(CardPresident... _cb) {
        HandPresident c_ = new HandPresident();
        c_.setCards(new IdList<CardPresident>(_cb));
        return c_;
    }

    private static void tryClickCard(ContainerSinglePresident _compo, MockGamePresident _mock) {
        tryClickCard(component(_compo,_mock.currentCards()));
    }
    private static AbsCustComponent component(ContainerSinglePresident _compo, HandPresident _cb) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        GamePresident game_ = _compo.partiePresident();
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying(), game_.isReversed());
        int f_ = _compo.userHand().getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelHand().getComponent(s_);
    }
    private static AbsCustComponent componentReceived(ContainerSinglePresident _compo, HandPresident _cb) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        GamePresident game_ = _compo.partiePresident();
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying(), game_.isReversed());
        HandPresident mainGiv_ = new HandPresident();
        mainGiv_.ajouterCartes(_compo.getVirtualHand());
        mainGiv_.sortCards(_compo.getDisplayingPresident().getDisplaying(), false);
        int f_ = mainGiv_.getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelHand().getComponent(s_);
    }
    private static AbsCustComponent componentGiven(ContainerSinglePresident _compo, HandPresident _cb) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_cb);
        GamePresident game_ = _compo.partiePresident();
        main_.sortCards(_compo.getDisplayingPresident().getDisplaying(), game_.isReversed());
        HandPresident mainGiv_ = new HandPresident();
        mainGiv_.ajouterCartes(_compo.getGivenCards());
        mainGiv_.sortCards(_compo.getDisplayingPresident().getDisplaying(), false);
        int f_ = mainGiv_.getCards().indexOfObj(main_.premiereCarte());
        int s_ = f_ + _cb.total() - 1;
        return _compo.getPanelGivenCards().getComponent(s_);
    }
    private ContainerSinglePresident editPresident(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresident(_mock);
        ContainerSinglePresident csb_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerPresident(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private ContainerSinglePresident editPresidentOtherDisplay(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresidentWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingPresident().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSinglePresident csb_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerPresident(edited(_deal, _rules));
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private GamePresident edited(DealPresident _deal, RulesPresident _rules) {
        GamePresident g_ = new GamePresident(GameType.EDIT, _deal, _rules, new Bytes());
        g_.setNombre();
        return g_;
    }

    private ContainerSinglePresident loadPresident(GamePresident _game, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresident(_mock);
        Games games_ = new Games();
        games_.jouerPresident(_game);
        wc_.tryToLoadDeal("_",games_);
        return (ContainerSinglePresident) wc_.getCore().getContainerGame();
    }
    private void tryClickNextPhase(ContainerSinglePresident _csb) {
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) _csb.window().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        tryClick((AbsButton)tr_.get(0));
        tryAnimate(_csb);
    }
    private DealPresident mix(int _d) {
        return new DealPresident(deal1(), (byte) _d);
    }

    private void nextCard(MockGamePresident _m, HandPresident _bid) {
        _m.getCards().add(_bid);
        //        return _sort.getNextPlayer((byte) _pl);
    }
    private RulesPresident rules() {
        RulesPresident r_ = new RulesPresident(4);
        r_.getCommon().setNbDeals(1);
        return r_;
    }
    private RulesPresident rules3() {
        RulesPresident r_ = new RulesPresident(3);
        r_.getCommon().setNbDeals(1);
        return r_;
    }

    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> deal2() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> deal3() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }

    static CustList<HandPresident> deal4() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        return hs_;
    }

}
