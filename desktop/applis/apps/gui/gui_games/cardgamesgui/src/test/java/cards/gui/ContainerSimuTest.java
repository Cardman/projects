package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
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
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_, new BeloteSampleFirstDeal(), rulesBelote());
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
    private ContainerBelote editBeloteOtherDisplay(MockGameBelote _mock, IntFirstDealBelote _d, RulesBelote _rules) {
        WindowCards wc_ = frameSimuBeloteWithEnd(_mock);
        wc_.getCore().getFacadeCards().setReglesBelote(_rules);
        wc_.getCore().setFirstDealBelote(_d);
        tryClick(wc_.getDemoGames().getVal(GameEnum.BELOTE));
        return (ContainerBelote) wc_.getCore().getContainerGame();
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
}
