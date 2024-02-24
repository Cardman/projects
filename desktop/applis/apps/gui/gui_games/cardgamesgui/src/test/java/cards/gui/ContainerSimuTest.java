package cards.gui;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
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

public final class ContainerSimuTest extends EquallableCardsGuiUtil {
    @Test
    public void s1() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.playMock(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(2, tr_.size());
    }
    @Test
    public void s2() {
        MockGameBelote mock_ = new MockGameBelote();
        BeloteSampleFirstDeal.playMock(mock_);
        ContainerBelote csb_ = editBeloteOtherDisplay(mock_);
        csb_.getArretDemo().set(AbstractSimulatingBelote.STATE_STOPPED);
        tryAnimate(csb_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) csb_.getOwner().getPane()).getTreeAccessible();
        assertEq(4, tr_.size());
    }
    private ContainerBelote editBeloteOtherDisplay(MockGameBelote _mock) {
        WindowCards wc_ = frameSimuBeloteWithEnd(_mock);
        wc_.getCore().getFacadeCards().setReglesBelote(rulesBelote());
        wc_.getCore().setFirstDealBelote(new BeloteSampleFirstDeal());
        tryClick(wc_.getDemoGames().getVal(GameEnum.BELOTE));
        return (ContainerBelote) wc_.getCore().getContainerGame();
    }
    private RulesBelote rulesBelote() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
}
