package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.gui.dialogs.*;
import cards.president.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.consts.*;
import cards.facade.enumerations.*;
import cards.gui.animations.*;
import cards.gui.containers.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class ContainerSimuTest extends EquallableCardsGuiUtil {
    @Test
    public void s1() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.playMock(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDeal(), rulesBelote());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s2() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.playMock(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new DefFirstDealBelote(), rulesBelote());
        csb_.getArretDemo().set(AbstractSimulatingBelote.STATE_STOPPED);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void s3() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.passDeal(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDeal(), rulesBelote());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s4() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDealAll.playMockDealAll(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDealAll(), rulesBeloteDealAll());
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s5() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDealAll.playMockDealAllSlam(mock_);
        RulesBelote rules_ = rulesBeloteDealAll();
        rules_.getAllowedDeclares().put(DeclaresBelote.HUNDRED, BoolVal.TRUE);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDealAll(), rules_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s6() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.playMock(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDeal(), rulesBelote());
        ((SimulationGameBelote)csb_.getAllThreads().get(0).getRunnable()).getSimulatingBelote().getStopEvent().action();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void s7() {
        RulesPresident r_ = rulesPresident();
        r_.setLooserStartsFirst(false);
        r_.getCommon().setNbDeals(2);
        MockGamePresident mock_ = new MockGamePresident();
        PresidentSampleFirstDeal.mockGameAfter(mock_);
        ContainerPresident csp_ = editPresidentOtherDisplay(mock_, new PresidentSampleFirstDeal(), r_);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s8() {
        RulesPresident r_ = rulesPresident();
        r_.setLooserStartsFirst(false);
        r_.getCommon().setNbDeals(2);
        MockGamePresident mock_ = new MockGamePresident();
        PresidentSampleFirstDeal.mockGame(mock_);
        ContainerPresident csp_ = editPresidentOtherDisplay(mock_, new DefFirstDealPresident(), r_);
        csp_.getArretDemo().set(AbstractSimulatingPresident.STATE_STOPPED);
        tryAnimate(csp_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void s9() {
        RulesPresident r_ = rulesPresident();
        r_.setLooserStartsFirst(false);
        r_.getCommon().setNbDeals(2);
        MockGamePresident mock_ = new MockGamePresident();
        PresidentSampleFirstDeal.mockGame(mock_);
        ContainerPresident csp_ = editPresidentOtherDisplay(mock_, new PresidentSampleFirstDeal(), r_);
        ((SimulationGamePresident)csp_.getAllThreads().get(0).getRunnable()).getSimulatingPresident().getStopEvent().action();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csp_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void s10() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu1(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s11() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu2(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s12() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu3(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s13() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu4(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s14() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDealAutoCall.simu1(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDealAutoCall(), rulesTarotWithCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s15() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDealAutoCall.simu2(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDealAutoCall(), rulesTarotWithCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s16() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDealAutoCall.simu3(mock_);
        RulesTarot r_ = rulesTarotWithCall1();
        r_.setDiscardAfterCall(false);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDealAutoCall(), r_);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s17() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDealNoCall.simu1(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDealNoCall(), rulesTarotWithoutCall2());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s18() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu5(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s19() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu5(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new DefFirstDealTarot(), rulesTarotWithoutCall1());
        cst_.getArretDemo().set(AbstractSimulatingTarot.STATE_STOPPED);
        tryAnimate(cst_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    @Test
    public void s20() {
        MockGameTarot mock_ = new MockGameTarot();
        TarotSampleFirstDeal.simu5(mock_);
        ContainerTarot cst_ = editTarotOtherDisplay(mock_, new TarotSampleFirstDeal(), rulesTarotWithoutCall1());
        ((SimulationGameTarot)cst_.getAllThreads().get(0).getRunnable()).getSimulatingTarot().getStopEvent().action();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) cst_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    private ContainerBelote editBeloteOtherDisplay(MockGameBelote _mock, IntFirstDealBelote _d, RulesBelote _rules) {
        WindowCards wc_ = frameSimuBeloteWithEnd(_mock);
        wc_.getCore().getFacadeCards().setReglesBelote(_rules);
        wc_.getCore().setFirstDealBelote(_d);
        tryClick(wc_.getDemoGames().getVal(GameEnum.BELOTE));
        return (ContainerBelote) wc_.getCore().getContainerGame();
    }
    private ContainerPresident editPresidentOtherDisplay(MockGamePresident _mock, IntFirstDealPresident _d, RulesPresident _rules) {
        WindowCards wc_ = frameSimuPresidentWithEnd(_mock);
        wc_.getCore().getFacadeCards().setReglesPresident(_rules);
        wc_.getCore().setFirstDealPresident(_d);
        wc_.getCore().getFacadeCards().getDisplayingPresident().setNbDeals(2);
        tryClick(wc_.getDemoGames().getVal(GameEnum.PRESIDENT));
        return (ContainerPresident) wc_.getCore().getContainerGame();
    }
    private ContainerTarot editTarotOtherDisplay(MockGameTarot _mock, IntFirstDealTarot _d, RulesTarot _rules) {
        WindowCards wc_ = frameSimuTarotWithEnd(_mock);
        wc_.getCore().getFacadeCards().setReglesTarot(_rules);
        wc_.getCore().setFirstDealTarot(_d);
        tryClick(wc_.getDemoGames().getVal(GameEnum.TAROT));
        return (ContainerTarot) wc_.getCore().getContainerGame();
    }
    private RulesBelote rulesBelote() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
    private RulesBelote rulesBeloteDealAll() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
    private RulesPresident rulesPresident() {
        RulesPresident r_ = new RulesPresident(4);
        r_.getCommon().setNbDeals(1);
        return r_;
    }
    private RulesTarot rulesTarotWithoutCall1() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setAllowedHandfuls(DialogTarot.poigneesAutoriseesMap(DealingTarot.DEAL_1_VS_4.getNombreCartesParJoueur()));
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingTarot.DEAL_1_VS_4);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        return rules_;
    }
    private RulesTarot rulesTarotWithoutCall2() {
        RulesTarot rules_ = new RulesTarot((byte) 6);
        rules_.setAllowedHandfuls(DialogTarot.poigneesAutoriseesMap(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreCartesChien()));
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        return rules_;
    }
    private RulesTarot rulesTarotWithCall1() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidTarot.SLAM,BoolVal.TRUE);
        rules_.getMiseres().add(Miseres.LOW_CARDS);
        return rules_;
    }
}
