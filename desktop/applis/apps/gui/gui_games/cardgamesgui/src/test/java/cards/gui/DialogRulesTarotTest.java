package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.dialogs.DialogRulesTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class DialogRulesTarotTest extends EquallableCardsGuiUtil{
    @Test
    public void init() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        assertTrue(fr_.getDialogRulesTarot().getCardDialog().isVisible());
        assertNull(fr_.getDialogRulesTarot().getNbGames());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getDialogRulesTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.TRUMP)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.SUIT)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.POINT)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.CHARACTER)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getBids().getVal(BidTarot.TAKE)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getBids().getVal(BidTarot.GUARD_WITHOUT)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getBids().getVal(BidTarot.GUARD_AGAINST)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getBids().getVal(BidTarot.SLAM)));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getAllowPlayCalledSuit()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getDiscardAfterCall()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getNbAtoutsPoignee()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getListeChoixTwo().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getListeChoixThree().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getListeChoixFour().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getListeChoixFive().self()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getBoutonPoignees()));
        assertTrue(tr_.containsObj(fr_.getDialogRulesTarot().getValidateButton()));
        assertTrue(fr_.getDialogRulesTarot().getDiscardAfterCall().isSelected());
        assertTrue(fr_.getDialogRulesTarot().getAllowPlayCalledSuit().isSelected());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        tryToggle(fr_.getDialogRulesTarot().getDiscardAfterCall());
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        assertFalse(fr_.getDialogRulesTarot().getCardDialog().isVisible());
        RulesTarot rules_ = fr_.getDialogRulesTarot().getReglesTarot();
        assertFalse(rules_.getDiscardAfterCall());
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        tryToggle(fr_.getDialogRulesTarot().getAllowPlayCalledSuit());
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        assertFalse(fr_.getDialogRulesTarot().getCardDialog().isVisible());
        RulesTarot rules_ = fr_.getDialogRulesTarot().getReglesTarot();
        assertFalse(rules_.isAllowPlayCalledSuit());
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        tryToggle(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.LOW_CARDS));
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        assertTrue(fr_.getDialogRulesTarot().getMiseres().getVal(Miseres.LOW_CARDS).isSelected());
    }
    @Test
    public void validate4() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        fr_.getDialogRulesTarot().getListeChoixFour().selectItem(1);
        fr_.getDialogRulesTarot().getListeChoixFour().getCombo().getSelections().get(0).valueChanged(null);
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        assertEq(1,fr_.getDialogRulesTarot().getListeChoixFour().getSelectedIndex());
    }
    @Test
    public void validate5() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        fr_.getDialogRulesTarot().getNbJoueurs().setValue(5);
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        assertEq(5,fr_.getDialogRulesTarot().getNbJoueurs().getValue());
    }
    @Test
    public void validate6() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        fr_.getDialogRulesTarot().getNbJoueurs().setValue(4);
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        assertEq(4,fr_.getDialogRulesTarot().getNbJoueurs().getValue());
    }
    @Test
    public void validate7() {
        WindowCards fr_ = frameRulesTarot();
        tryClick(fr_.getRulesGames().getVal(GameEnum.TAROT));
        fr_.getDialogRulesTarot().getListeChoixFive().selectItem(0);
        fr_.getDialogRulesTarot().getListeChoixFive().getCombo().getSelections().get(0).valueChanged(null);
        fr_.getDialogRulesTarot().getNbAtoutsPoignee().setValue(7);
        tryClick(fr_.getDialogRulesTarot().getBoutonPoignees());
        tryClick(fr_.getDialogRulesTarot().getValidateButton());
        RulesTarot rules_ = fr_.getDialogRulesTarot().getReglesTarot();
        assertEq(7,rules_.getAllowedHandfuls().getVal(Handfuls.ONE));
    }
    @Test
    public void fixNbPlayer() {
        WindowCards fr_ = frameRulesTarot();
        DialogRulesTarot d_ = new DialogRulesTarot(fr_.getFrames());
        d_.setDialogue(false,5,fr_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) d_.getCardDialog().getPane()).getTreeAccessible();
        assertEq(19, tr_.size());
        assertTrue(tr_.containsObj(d_.getMiseres().getVal(Miseres.TRUMP)));
        assertTrue(tr_.containsObj(d_.getMiseres().getVal(Miseres.SUIT)));
        assertTrue(tr_.containsObj(d_.getMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(tr_.containsObj(d_.getMiseres().getVal(Miseres.POINT)));
        assertTrue(tr_.containsObj(d_.getMiseres().getVal(Miseres.CHARACTER)));
        assertTrue(tr_.containsObj(d_.getBids().getVal(BidTarot.TAKE)));
        assertTrue(tr_.containsObj(d_.getBids().getVal(BidTarot.GUARD_WITHOUT)));
        assertTrue(tr_.containsObj(d_.getBids().getVal(BidTarot.GUARD_AGAINST)));
        assertTrue(tr_.containsObj(d_.getBids().getVal(BidTarot.SLAM)));
        assertTrue(tr_.containsObj(d_.getAllowPlayCalledSuit()));
        assertTrue(tr_.containsObj(d_.getDiscardAfterCall()));
        assertTrue(tr_.containsObj(d_.getNbAtoutsPoignee()));
        assertTrue(tr_.containsObj(d_.getListeChoix().self()));
        assertTrue(tr_.containsObj(d_.getListeChoixTwo().self()));
        assertTrue(tr_.containsObj(d_.getListeChoixThree().self()));
        assertTrue(tr_.containsObj(d_.getListeChoixFour().self()));
        assertTrue(tr_.containsObj(d_.getListeChoixFive().self()));
        assertTrue(tr_.containsObj(d_.getBoutonPoignees()));
        assertTrue(tr_.containsObj(d_.getValidateButton()));
        assertTrue(d_.getDiscardAfterCall().isSelected());
        assertTrue(d_.getAllowPlayCalledSuit().isSelected());
    }
}
