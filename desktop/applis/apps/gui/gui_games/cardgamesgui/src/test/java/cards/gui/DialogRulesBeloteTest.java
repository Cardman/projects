package cards.gui;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.DialogBelote;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DialogRulesBeloteTest extends EquallableCardsGuiUtil{
    @Test
    public void init() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        assertTrue(fr_.getDialogRulesBelote().getCardDialog().isVisible());
        assertNull(fr_.getDialogRulesBelote().getNbGames());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getDialogRulesBelote().getCardDialog().getPane()).getTreeAccessible();
        assertEq(19, tr_.size());
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDealAll()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getClassic()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getUnderTrumpingFoe()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getListChoiceTwo().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.NO_TRUMP)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.ALL_TRUMP)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.THIRTY)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.HUNDRED)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_7)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_8)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_9)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_10)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_JACK)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_QUEEN)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_KING)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesBelote().getValidateButton()));
        assertFalse(fr_.getDialogRulesBelote().getDealAll().isSelected());
        assertTrue(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.FOLD).isSelected());
        assertTrue(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.SUIT).isSelected());
        assertTrue(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.OTHER_SUIT).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.NO_TRUMP).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.ALL_TRUMP).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.THIRTY).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.HUNDRED).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_7).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_8).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_9).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_10).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_JACK).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_QUEEN).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_KING).isSelected());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        tryToggle(fr_.getDialogRulesBelote().getBids().getVal(BidBelote.NO_TRUMP));
        tryClick(fr_.getDialogRulesBelote().getValidateButton());
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
        tryToggle(fr_.getDialogRulesBelote().getDealAll());
        tryToggle(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY));
        tryClick(fr_.getDialogRulesBelote().getValidateButton());
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
        tryToggle(fr_.getDialogRulesBelote().getDealAll());
        tryToggle(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY));
        tryClick(fr_.getDialogRulesBelote().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        assertTrue(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FIFTY).isSelected());
        assertFalse(fr_.getDialogRulesBelote().getDeclares().getVal(DeclaresBelote.FOUR_1).isSelected());
    }
    @Test
    public void validate4() {
        WindowCards fr_ = frameRulesBelote();
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        tryToggle(fr_.getDialogRulesBelote().getDealAll());
        IdList<BeloteTrumpPartner> l_ = new IdList<BeloteTrumpPartner>(DialogBelote.allBeloteTrumpPartner());
        int index_ = l_.indexOfObj(BeloteTrumpPartner.OVERTRUMP_ONLY);
        fr_.getDialogRulesBelote().getListChoiceTwo().selectItem(index_);
        tryClick(fr_.getDialogRulesBelote().getValidateButton());
        assertEq(BeloteTrumpPartner.OVERTRUMP_ONLY,fr_.getReglesBelote().getGestionCoupePartenaire());
        tryClick(fr_.getRulesGames().getVal(GameEnum.BELOTE));
        assertEq(BeloteTrumpPartner.OVERTRUMP_ONLY,fr_.getDialogRulesBelote().getListChoiceTwo().getCurrentElement());
        assertEq(index_,fr_.getDialogRulesBelote().getListChoiceTwo().getSelectedIndex());
    }
}
