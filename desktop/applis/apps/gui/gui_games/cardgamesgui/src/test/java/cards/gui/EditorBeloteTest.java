package cards.gui;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.enumerations.GameEnum;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class EditorBeloteTest extends EquallableCardsGuiUtil {
    @Test
    public void init() {
        WindowCards fr_ = frameEditorBelote();
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorBelote().getCardDialog().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDealAll()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getClassic()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getNbGames()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getUnderTrumpingFoe()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getListChoiceTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getBids().getVal(BidBelote.NO_TRUMP)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getBids().getVal(BidBelote.ALL_TRUMP)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.THIRTY)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FIFTY)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.HUNDRED)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_1)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_7)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_8)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_9)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_10)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_JACK)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_QUEEN)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_KING)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getValidateRules()));
        assertFalse(fr_.getEditorBelote().getDealAll().isSelected());
        assertTrue(fr_.getEditorBelote().getBids().getVal(BidBelote.FOLD).isSelected());
        assertTrue(fr_.getEditorBelote().getBids().getVal(BidBelote.SUIT).isSelected());
        assertTrue(fr_.getEditorBelote().getBids().getVal(BidBelote.OTHER_SUIT).isSelected());
        assertFalse(fr_.getEditorBelote().getBids().getVal(BidBelote.NO_TRUMP).isSelected());
        assertFalse(fr_.getEditorBelote().getBids().getVal(BidBelote.ALL_TRUMP).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.THIRTY).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FIFTY).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.HUNDRED).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_1).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_7).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_8).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_9).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_10).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_JACK).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_QUEEN).isSelected());
        assertFalse(fr_.getEditorBelote().getDeclares().getVal(DeclaresBelote.FOUR_QUEEN).isSelected());
    }
    @Test
    public void rules1() {
        WindowCards wc_ = frameEditorBelote();
        tryClick(wc_.getEditGames().getVal(GameEnum.BELOTE));
        assertTrue(wc_.getEditorBelote().getCardDialog().isVisible());
        assertNotNull(wc_.getEditorBelote().getNbGames());
    }
}
