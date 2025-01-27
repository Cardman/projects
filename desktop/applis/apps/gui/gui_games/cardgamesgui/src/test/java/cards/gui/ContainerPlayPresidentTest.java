package cards.gui;

import cards.consts.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.main.CardsNonModalEvent;
import cards.president.*;
import cards.president.enumerations.*;
import code.gui.*;
import code.mock.*;
import code.stream.*;
import code.util.*;
import code.util.core.*;
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
        dealMock(mock_, csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
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
        assertEq(13,th_.getDistribution().hand((byte) 1).total());
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
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),2);
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
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),3);
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
        assertFalse(csp_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
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
        assertFalse(csp_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
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
        assertFalse(csp_.window().getCore().getReportingFrame().getReport().getText().isEmpty());
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
        nextCard(mock_,create(CardPresident.HEART_3));
        nextCard(mock_,create(CardPresident.DIAMOND_7));
        ContainerSinglePresident csp_ = oneDeal3(deal_, mock_, r_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(12, tr2_.size());
        assertTrue(tr2_.containsObj(csp_.getNoPlay()));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_7,CardPresident.CLUB_7))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_8,CardPresident.HEART_8))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.CLUB_9))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_10))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.SPADE_KING))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.DIAMOND_1))));
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
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
    @Test
    public void p32() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        GamePresident gp_ = edited(deal_, r_);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_3));
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = loadPresident(gp_, mock_);
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
    public void p33() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(3);
        GamePresident gp_ = edited(deal_, r_);
        MockGamePresident mock_ = new MockGamePresident();
        ContainerSinglePresident csp_ = loadPresident(gp_, mock_);
        display(csp_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(13, tr2_.size());
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
        assertTrue(tr2_.containsObj(component(csp_,create(CardPresident.HEART_2))));
    }
    @Test
    public void p34() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        GamePresident gp_ = edited(deal_, r_);
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_3));
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.SPADE_5));
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = loadPresident(gp_, mock_);
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
    public void p35() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        GamePresident gp_ = edited(deal_, r_);
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_3));
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_5));
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_, create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = loadPresident(gp_, mock_);
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
    public void p36() {
        RulesPresident r_ = rules();
        DealPresident deal_ = mix(0);
        GamePresident gp_ = edited(deal_, r_);
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_3));
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_5));
        gp_.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_6));
        MockGamePresident mock_ = new MockGamePresident();
        ContainerSinglePresident csp_ = loadPresident(gp_, mock_);
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
    public void p37() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_,create(CardPresident.HEART_3));
        nextCard(mock_,create(CardPresident.HEART_6));
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        GamePresident gp2_ = edited(dealSec_, r_, gp_.getNewRanks());
        gp2_.initCartesEchanges();
        gp2_.donnerMeilleuresCartes();
        gp2_.getSwitchedCards().get(3).ajouterCartes(create(CardPresident.DIAMOND_3));
        gp2_.giveWorstCards(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        ContainerSinglePresident csp_ = loadPresident(gp2_, mock_);
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
    public void p38() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_,create(CardPresident.HEART_6));
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        GamePresident gp2_ = edited(dealSec_, r_, gp_.getNewRanks());
        gp2_.initCartesEchanges();
        gp2_.donnerMeilleuresCartes();
        gp2_.getSwitchedCards().get(3).ajouterCartes(create(CardPresident.DIAMOND_3));
        gp2_.giveWorstCards(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        gp2_.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_3));
        ContainerSinglePresident csp_ = loadPresident(gp2_, mock_);
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
    public void p39() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        GamePresident gp2_ = edited(dealSec_, r_, gp_.getNewRanks());
        gp2_.initCartesEchanges();
        gp2_.donnerMeilleuresCartes();
        gp2_.getSwitchedCards().get(3).ajouterCartes(create(CardPresident.DIAMOND_3));
        gp2_.giveWorstCards(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        gp2_.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_3));
        gp2_.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_6));
        ContainerSinglePresident csp_ = loadPresident(gp2_, mock_);
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
    public void p40() {
        RulesPresident r_ = rules3();
        r_.setPossibleReversing(false);
        r_.setEqualty(EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL);
        r_.getCommon().setNbDeals(2);
        r_.setLooserStartsFirst(true);
        DealPresident deal_ = new DealPresident(deal4(), (byte) 2);
        DealPresident dealSec_ = new DealPresident(deal4(), (byte) 1);
        MockGamePresident mock_ = new MockGamePresident();
        GamePresident gp_ = edited(deal_, r_);
        mockReGame4(gp_);
        GamePresident gp2_ = edited(dealSec_, r_, gp_.getNewRanks());
        gp2_.initCartesEchanges();
        gp2_.donnerMeilleuresCartes();
        gp2_.getSwitchedCards().get(2).ajouterCartes(create(CardPresident.DIAMOND_3));
        gp2_.giveWorstCards(create());
        mock_.getStacks().add(dealSec_);
        ContainerSinglePresident csp_ = loadPresident(gp2_, mock_);
        tryAnimate(csp_);
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
    public void p41() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        DealPresident dealSec_ = new DealPresident(deal1(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_,create(CardPresident.HEART_3));
        nextCard(mock_,create(CardPresident.HEART_6));
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        GamePresident gp2_ = edited(dealSec_, r_, gp_.getNewRanks());
        gp2_.initCartesEchanges();
        gp2_.donnerMeilleuresCartes();
        gp2_.getSwitchedCards().get(3).ajouterCartes(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = loadPresident(gp2_, mock_);
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
    public void p42() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        nextCard(mock_,create(CardPresident.HEART_3));
        nextCard(mock_,create(CardPresident.HEART_6));
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        ContainerSinglePresident csp_ = loadPresidentOtherDisplay(gp_, mock_);
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr2_.size());
        assertTrue(tr2_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr2_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr2_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr2_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr2_.containsObj(csp_.getNextDeal()));
    }
    @Test
    public void p43() {
        RulesPresident r_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        GamePresident gp_ = new GamePresident(GameType.RANDOM, deal_, r_, new Bytes());
        mockReGame(gp_);
        ContainerSinglePresident csp_ = loadPresidentOtherDisplay(gp_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p44() {
        RulesPresident r_ = rules();
        r_.getCommon().setNbDeals(2);
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        GamePresident gp_ = edited(deal_, r_);
        mockReGame(gp_);
        ContainerSinglePresident csp_ = loadPresidentOtherDisplay(gp_,mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p45() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = modifyPresident(rules_, mock_);
        dealMock(mock_, csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p46() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = modifyPresident(rules_, mock_);
        dealMock(mock_, csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p47() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = modifyPresident(rules_, mock_,1);
        dealMock(mock_, csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p48() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        GamePresident gb_ = new GamePresident(GameType.RANDOM,deal_,rules_,new Bytes());
        mockReGame(gb_);
        ContainerSinglePresident csp_ = loadPresidentOtherDisplay(gb_, mock_, 1);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p49() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        ContainerSinglePresident csp_ = modifyPresident(rules_, mock_);
        dealMock(mock_, csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csp_),StringUtil.join(FacadeCards.defInfos(), "\n"),csp_.window().getFrames().getStreams());
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p50() {
        RulesPresident rules_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mock_.getStacks().add(new DealPresident(deal_));
        mock_.getSw().add(create(CardPresident.DIAMOND_3));
        GamePresident gb_ = new GamePresident(GameType.RANDOM,deal_,rules_,new Bytes());
        mockReGame(gb_);
        ContainerSinglePresident csp_ = loadPresidentOtherDisplay(gb_, mock_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.window().getPane()).getTreeAccessible();
        assertEq(5, tr_.size());
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
        StreamTextFile.saveTextFile("/"+stack(csp_),StringUtil.join(FacadeCards.defInfos(), "\n"),csp_.window().getFrames().getStreams());
        tryClick(csp_.getNextDeal());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p51() {
        RulesPresident r_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(r_,deal_,mock_);
        dealMock(mock_, csp_);
        tryClick(csp_.getContent().getReplayButton());
        assertEq(4,csp_.partiePresident().getDeal().nombreDeMains());
    }
    @Test
    public void p52() {
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
        assertTrue(CardsNonModalEvent.aliveEvents(null,csp_.window()));
        tryClick(csp_.window().getHelpGame());
        assertTrue(csp_.window().getHelpGame().isEnabled());
        assertFalse(CardsNonModalEvent.enabledEvents(csp_));
        assertFalse(CardsNonModalEvent.aliveEvents(null,csp_.window()));
        assertTrue(CardsNonModalEvent.aliveEvents(null,null));
        tryClickCard(csp_,mock_);
        assertEq(0,factory(csp_).size());
    }
    @Test
    public void p53() {
        RulesPresident r_ = rules();
        DealPresident deal_ = new DealPresident(deal2(), (byte) 0);
        MockGamePresident mock_ = new MockGamePresident();
        mockGame(mock_);
        ContainerSinglePresident csp_ = editPresidentOtherDisplay(r_,deal_,mock_);
        dealMock(mock_, csp_);
        tryClick(csp_.getContent().getStopButton());
        assertFalse(((MockCustComponent) csp_.window().getPane()).getTreeAccessible().isEmpty());
    }
    @Test
    public void p54() {
        RulesPresident r_ = rules();
        r_.setEqualty(EqualtyPlaying.SKIP_ALWAYS_NEXT);
        DealPresident deal_ = new DealPresident(dealPres(),(byte) 3);
        MockGamePresident mock_ = mockGamePresident();
        ContainerSinglePresident csp_ = editPresident(r_,deal_,mock_);
        display(csp_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClickCard(csp_,mock_);
        tryAnimate(csp_);
        tryClick(csp_.window().getTricksHands());
        eventsCombo(csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTrickNumber().getCombo(),2);
        TricksHandsPresident th_ = csp_.window().getDialogTricksPresident().getPanelTricksHandsPresident().getTricksHands();
        assertEq(4,th_.getDistribution().nombreDeMains());
        assertEq(11,th_.getDistribution().hand((byte) 0).total());
        assertEq(12,th_.getDistribution().hand((byte) 1).total());
        assertEq(12,th_.getDistribution().hand((byte) 2).total());
        assertEq(12,th_.getDistribution().hand((byte) 3).total());
    }

    static CustList<HandPresident> dealPres() {
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
    static MockGamePresident mockGamePresident() {
        MockGamePresident m_ = new MockGamePresident();
        nextCard(m_, create(CardPresident.CLUB_3));
        nextCard(m_, create(CardPresident.SPADE_3));
        nextCard(m_, create(CardPresident.DIAMOND_7));
        nextCard(m_, create(CardPresident.SPADE_7));
        nextCard(m_, create(CardPresident.HEART_7));
        return m_;
    }
    private void dealMock(MockGamePresident _mock, ContainerSinglePresident _csp) {
        display(_csp);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClick(_csp.getNoPlay());
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
        tryClickCard(_csp, _mock);
        tryAnimate(_csp);
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
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
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
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
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
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getTrickNumber().self()));
        assertTrue(tr_.containsObj(csp_.getPanelTricksHandsPresident().getCardNumberTrick().self()));
        assertTrue(tr_.containsObj(csp_.getContent().getReplayButton()));
        assertTrue(tr_.containsObj(csp_.getContent().getStopButton()));
        assertTrue(tr_.containsObj(csp_.getNextDeal()));
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

    private void mockReGame(GamePresident _mock) {
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_3));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_3));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_3));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_3));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_4,CardPresident.CLUB_4));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_4,CardPresident.HEART_4));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_7));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_7));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_7));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_7));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_8,CardPresident.DIAMOND_8));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_9));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_9));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_9));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_9));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_10));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_10));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_10));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_10));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_KING));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_KING));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_KING));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_KING));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_1));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_1));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_2));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_JACK,CardPresident.CLUB_JACK));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_2));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_5,CardPresident.HEART_5));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_6,CardPresident.CLUB_6));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_2,CardPresident.CLUB_2));
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

    private void mockReGame4(GamePresident _mock) {
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_3,CardPresident.SPADE_3));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_5,CardPresident.HEART_5));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_5,CardPresident.DIAMOND_5));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_6,CardPresident.DIAMOND_6));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_7,CardPresident.CLUB_7));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_8,CardPresident.CLUB_8));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_KING,CardPresident.DIAMOND_KING));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_QUEEN,CardPresident.DIAMOND_QUEEN,CardPresident.SPADE_QUEEN,CardPresident.CLUB_QUEEN));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_2,CardPresident.SPADE_2));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_1,CardPresident.CLUB_1));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_10));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_9));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_7));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_3));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_2));
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_1));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.CLUB_KING));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.DIAMOND_JACK,CardPresident.HEART_JACK));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_10,CardPresident.CLUB_10));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_9,CardPresident.HEART_9));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_7));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.SPADE_6,CardPresident.CLUB_6));
        _mock.addCardsToCurrentTrickAndLoop(create());
        _mock.addCardsToCurrentTrickAndLoop(create(CardPresident.HEART_3));
        _mock.addCardsToCurrentTrickAndLoop(create());
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
    private static HandPresident create(CardPresident... _cb) {
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
        ContainerSinglePresident csp_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csp_);
        csp_.editerPresident(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csp_;
    }

    private ContainerSinglePresident editPresidentOtherDisplay(RulesPresident _rules, DealPresident _deal, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresidentWithEnd(_mock);
        wc_.baseWindow().getFacadeCards().getDisplayingPresident().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSinglePresident csp_ = new ContainerSinglePresident(wc_);
        wc_.getCore().setContainerGame(csp_);
        csp_.editerPresident(edited(_deal, _rules));
        wc_.getChange().setEnabled(true);
        return csp_;
    }

    private GamePresident edited(DealPresident _deal, RulesPresident _rules) {
        return edited(_deal,_rules,new Bytes());
    }

    private GamePresident edited(DealPresident _deal, RulesPresident _rules, Bytes _rks) {
        GamePresident g_ = new GamePresident(GameType.EDIT, _deal, _rules, _rks);
        g_.setNombre();
        return g_;
    }

    private ContainerSinglePresident loadPresident(GamePresident _game, MockGamePresident _mock) {
        WindowCards wc_ = frameSinglePresident(_mock);
        return containerGame(_game, wc_);
    }
    private ContainerSinglePresident loadPresidentOtherDisplay(GamePresident _game, MockGamePresident _mock) {
        return loadPresidentOtherDisplay(_game,_mock,0);
    }

    private ContainerSinglePresident loadPresidentOtherDisplay(GamePresident _game, MockGamePresident _mock, int _i) {
        WindowCards wc_ = frameSinglePresidentWithEnd(_mock,_i);
        wc_.baseWindow().getFacadeCards().getDisplayingPresident().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        return containerGame(_game, wc_);
    }

    private ContainerSinglePresident containerGame(GamePresident _game, WindowCards _wc) {
        CheckerGamePresidentWithRules.check(_game, mesCheck());
        assertTrue(_game.getError().isEmpty());
        Games games_ = new Games();
        games_.jouerPresident(_game);
        _wc.tryToLoadDeal("_",games_);
        return (ContainerSinglePresident) _wc.getCore().getContainerGame();
    }
    public static StringMap<String> mesCheck(){
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(CheckerGamePresidentWithRules.ERROR_RULES,CheckerGamePresidentWithRules.ERROR_RULES);
        m_.addEntry(CheckerGamePresidentWithRules.HANDS_COUNT,CheckerGamePresidentWithRules.HANDS_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.SCORES_COUNT,CheckerGamePresidentWithRules.SCORES_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.TRICK_EVENTS,CheckerGamePresidentWithRules.TRICK_EVENTS);
        m_.addEntry(CheckerGamePresidentWithRules.EMPTY_TRICK,CheckerGamePresidentWithRules.EMPTY_TRICK);
        m_.addEntry(CheckerGamePresidentWithRules.NOT_PLAYABLE,CheckerGamePresidentWithRules.NOT_PLAYABLE);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_RANK_COUNT,CheckerGamePresidentWithRules.BAD_RANK_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_OTHER,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_OTHER);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT,CheckerGamePresidentWithRules.BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT);
        m_.addEntry(CheckerGamePresidentWithRules.DUPLICATE_RANK_COUNT,CheckerGamePresidentWithRules.DUPLICATE_RANK_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_CARD_COUNT,CheckerGamePresidentWithRules.BAD_CARD_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_CARD_UNIT_COUNT,CheckerGamePresidentWithRules.BAD_CARD_UNIT_COUNT);
        m_.addEntry(CheckerGamePresidentWithRules.NOT_ALLOWED_PLAYED_CARD,CheckerGamePresidentWithRules.NOT_ALLOWED_PLAYED_CARD);
        m_.addEntry(CheckerGamePresidentWithRules.MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP,CheckerGamePresidentWithRules.MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP);
        m_.addEntry(CheckerGamePresidentWithRules.MISS_MATCH_STRENGTH,CheckerGamePresidentWithRules.MISS_MATCH_STRENGTH);
        m_.addEntry(CheckerGamePresidentWithRules.BAD_PLAYED_CARD,CheckerGamePresidentWithRules.BAD_PLAYED_CARD);
        m_.addEntry(CheckerGamePresidentWithRules.FIRST_GROUP_CANNOT_BE_EMPTY,CheckerGamePresidentWithRules.FIRST_GROUP_CANNOT_BE_EMPTY);
        m_.addEntry(CheckerGamePresidentWithRules.CANNOT_PASS,CheckerGamePresidentWithRules.CANNOT_PASS);
        m_.addEntry(CheckerGamePresidentWithRules.NO_CARD_AFTER_FINISHED_DIRECTLY_CARD,CheckerGamePresidentWithRules.NO_CARD_AFTER_FINISHED_DIRECTLY_CARD);
        return m_;
    }
    private ContainerSinglePresident modifyPresident(RulesPresident _rules, MockGamePresident _mock) {
        return modifyPresident(_rules,_mock,0);
    }
    private ContainerSinglePresident modifyPresident(RulesPresident _rules, MockGamePresident _mock, int _i) {
        WindowCards wc_ = frameSinglePresidentWithEnd(_mock,_i);
        wc_.getCore().getFacadeCards().setReglesPresident(_rules);
        wc_.getCore().setFirstDealPresident(new PresidentSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getDisplayingPresident().getDisplaying().setClockwise(true);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.PRESIDENT));
        ContainerSinglePresident csp_ = (ContainerSinglePresident) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csp_;
    }
    private DealPresident mix(int _d) {
        return new DealPresident(deal1(), (byte) _d);
    }

    private static void nextCard(MockGamePresident _m, HandPresident _bid) {
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
//
//    private void dealMock(MockGamePresident _mock, ContainerSinglePresident _csp) {
//        display(_csp);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClick(_csp.getNoPlay());
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//        tryClickCard(_csp, _mock);
//        tryAnimate(_csp);
//    }

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
