package cards.gui;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.enumerations.GameEnum;
import code.gui.AbsButton;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DialogRulesBeloteTest extends EquallableCardsGuiUtil{
    @Test
    public void init() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        assertTrue(fr_.getDialogRulesBelote().getCardDialog().isVisible());
        assertNull(fr_.getDialogRulesBelote().getNbGames());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        tryCheck(fr_.getDialogRulesBelote().getDealAll(), false);
        tryCheck(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.NO_TRUMP), true);
        tryCheck(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.ALL_TRUMP), false);
        tryClick((AbsButton) fr_.getDialogRulesBelote().getCardDialog().getPane().getComponent(1));
        assertFalse(fr_.getDialogRulesBelote().getCardDialog().isVisible());
        RulesBelote rules_ = fr_.getDialogRulesBelote().getReglesBelote();
        assertEq(DealingBelote.CLASSIC_2_VS_2, rules_.getDealing());
        assertEq(BoolVal.TRUE,rules_.getAllowedBids().getVal(BidBelote.NO_TRUMP));
        assertEq(BoolVal.FALSE,rules_.getAllowedBids().getVal(BidBelote.ALL_TRUMP));
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        tryCheck(fr_.getDialogRulesBelote().getDealAll(), true);
        tryCheck(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY), true);
        tryCheck(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1), false);
        tryClick((AbsButton) fr_.getDialogRulesBelote().getCardDialog().getPane().getComponent(1));
        assertFalse(fr_.getDialogRulesBelote().getCardDialog().isVisible());
        RulesBelote rules_ = fr_.getDialogRulesBelote().getReglesBelote();
        assertEq(DealingBelote.COINCHE_2_VS_2, rules_.getDealing());
        assertEq(BoolVal.TRUE,rules_.getAllowedDeclares().getVal(DeclaresBelote.FIFTY));
        assertEq(BoolVal.FALSE,rules_.getAllowedDeclares().getVal(DeclaresBelote.FOUR_1));
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        tryCheck(fr_.getDialogRulesBelote().getDealAll(), true);
        tryCheck(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY), true);
        tryCheck(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1), false);
        tryClick((AbsButton) fr_.getDialogRulesBelote().getCardDialog().getPane().getComponent(1));
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        assertTrue(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1).isSelected());
    }
}
