package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.DialogRulesPresident;
import cards.president.RulesPresident;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class DialogRulesPresidentTest extends EquallableCardsGuiUtil{
    @Test
    public void init() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertTrue(fr_.getDialogRulesPresident().getCardDialog().isVisible());
        assertNull(fr_.getDialogRulesPresident().getNbGames());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getDialogRulesPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(10, tr_.size());
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getLooseFinishBestCards()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getPossibleReversing()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getCanPass()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getEquality().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getLooserStartsFirst()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getSwitchCards()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getNbStacks()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesPresident().getValidateButton()));
        assertFalse(fr_.getDialogRulesPresident().getPossibleReversing().isSelected());
        assertTrue(fr_.getDialogRulesPresident().getCanPass().isSelected());
        assertTrue(fr_.getDialogRulesPresident().getLooserStartsFirst().isSelected());
        assertTrue(fr_.getDialogRulesPresident().getLooseFinishBestCards().isSelected());
        assertTrue(fr_.getDialogRulesPresident().getSwitchCards().isSelected());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        tryToggle(fr_.getDialogRulesPresident().getPossibleReversing());
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        assertFalse(fr_.getDialogRulesPresident().getCardDialog().isVisible());
        RulesPresident rules_ = fr_.getDialogRulesPresident().getReglesPresident();
        assertTrue(rules_.isPossibleReversing());
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        tryToggle(fr_.getDialogRulesPresident().getCanPass());
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        assertFalse(fr_.getDialogRulesPresident().getCardDialog().isVisible());
        RulesPresident rules_ = fr_.getDialogRulesPresident().getReglesPresident();
        assertTrue(rules_.isHasToPlay());
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        tryToggle(fr_.getDialogRulesPresident().getCanPass());
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertFalse(fr_.getDialogRulesPresident().getCanPass().isSelected());
    }
    @Test
    public void validate4() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        eventsCombo(fr_.getDialogRulesPresident().getEquality().getCombo(), 1);
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertEq(1,fr_.getDialogRulesPresident().getEquality().getSelectedIndex());
    }
    @Test
    public void validate5() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        fr_.getDialogRulesPresident().getNbJoueurs().setValue(5);
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        assertEq(5,fr_.getDialogRulesPresident().getNbJoueurs().getValue());
    }
    @Test
    public void validate6() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        fr_.getDialogRulesPresident().getNbJoueurs().setValue(20);
        fr_.getDialogRulesPresident().getNbStacks().setValue(RulesPresident.getNbMaxStacks(20));
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertEq(20,fr_.getDialogRulesPresident().getNbJoueurs().getValue());
        assertEq(RulesPresident.getNbMaxStacks(20),fr_.getDialogRulesPresident().getNbStacks().getValue());
        fr_.getDialogRulesPresident().getNbJoueurs().setValue(6);
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertEq(6,fr_.getDialogRulesPresident().getNbJoueurs().getValue());
    }
    @Test
    public void validate7() {
        WindowCards fr_ = frameRulesPresident();
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        eventsCombo(fr_.getDialogRulesPresident().getEquality().getCombo(), 1);
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        eventsCombo(fr_.getDialogRulesPresident().getEquality().getCombo(), 2);
        tryClick(fr_.getDialogRulesPresident().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.PRESIDENT));
        assertEq(2,fr_.getDialogRulesPresident().getEquality().getSelectedIndex());
    }
    @Test
    public void fixNbPlayer() {
        WindowCards fr_ = frameRulesPresident();
        DialogRulesPresident d_ = new DialogRulesPresident(fr_.getFrames());
        d_.setDialogue(false,5,fr_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) d_.getCardDialog().getPane()).getTreeAccessible();
        assertEq(9, tr_.size());
        assertTrue(tr_.containsObj(d_.getLooseFinishBestCards()));
        assertTrue(tr_.containsObj(d_.getPossibleReversing()));
        assertTrue(tr_.containsObj(d_.getCanPass()));
        assertTrue(tr_.containsObj(d_.getListeChoix().self()));
        assertTrue(tr_.containsObj(d_.getEquality().self()));
        assertTrue(tr_.containsObj(d_.getLooserStartsFirst()));
        assertTrue(tr_.containsObj(d_.getSwitchCards()));
        assertTrue(tr_.containsObj(d_.getNbStacks()));
        assertTrue(tr_.containsObj(d_.getValidateButton()));
        assertFalse(d_.getPossibleReversing().isSelected());
        assertTrue(d_.getCanPass().isSelected());
        assertTrue(d_.getLooserStartsFirst().isSelected());
        assertTrue(d_.getLooseFinishBestCards().isSelected());
        assertTrue(d_.getSwitchCards().isSelected());
    }
}
