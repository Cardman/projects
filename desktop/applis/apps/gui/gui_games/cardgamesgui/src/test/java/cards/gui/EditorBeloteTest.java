package cards.gui;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.panels.BeloteCardsScrollableList;
import code.gui.AbsCustComponent;
import code.gui.ScrollCustomGraphicList;
import code.mock.MockCustComponent;
import code.util.IdList;
import code.util.Ints;
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
    public void validate1() {
        WindowCards fr_ = frameMini("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        assertEq(12, fr_.getEditorBelote().getRemaining().getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorBelote().getCardDialog().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameMini("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryToggle(fr_.getEditorBelote().getDealAll());
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        assertEq(0, fr_.getEditorBelote().getRemaining().getMax());
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameMini("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        assertEq(12, fr_.getEditorBelote().getRemaining().getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorBelote().getCardDialog().getPane()).getTreeAccessible();
        assertEq(18, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void deplacer1() {
        WindowCards fr_ = frameMini("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> hand_ = stack_.valMain();
        input_.select(Ints.newList(hand_.indexOfObj(CardBelote.HEART_1),hand_.indexOfObj(CardBelote.HEART_8)));
        input_.events();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(1).valMain();
        assertEq(2,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_8,result_.get(1));
        assertEq(30,stack_.valMain().size());
    }
    @Test
    public void deplacer2() {
        WindowCards fr_ = frameMini("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valMain();
        input_.select(Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_8)));
        input_.events();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valMain();
        input_.select(Ints.newList(handSecond_.indexOfObj(CardBelote.HEART_10)));
        input_.events();
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(1).valMain();
        assertEq(3,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_10,result_.get(1));
        assertEq(CardBelote.HEART_8,result_.get(2));
        assertEq(29,stack_.valMain().size());
    }
    @Test
    public void deplacer3() {
        WindowCards fr_ = frameMini("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valMain();
        input_.select(Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_8)));
        input_.events();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valMain();
        input_.select(Ints.newList(handSecond_.indexOfObj(CardBelote.HEART_10)));
        input_.events();
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(5).valMain();
        assertEq(3,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_8,result_.get(1));
        assertEq(CardBelote.HEART_10,result_.get(2));
        assertEq(29,stack_.valMain().size());
    }
}
