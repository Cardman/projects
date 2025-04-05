package cards.gui;

import cards.facade.enumerations.*;
import cards.gui.panels.*;
import cards.solitaire.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorSolitaireTest extends EquallableCardsGuiUtil {
    @Test
    public void init1() {
        WindowCards fr_ = frameEditorSolitaire();
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        assertTrue(fr_.getEditorClassic().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorClassic().getCardDialog().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(7).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().stackHands().get(8).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(compo(0, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(1, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(2, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(3, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void init2() {
        WindowCards fr_ = frameEditorSolitaire();
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        assertTrue(fr_.getEditorFreeCell().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorFreeCell().getCardDialog().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(7).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().stackHands().get(8).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(compo(0, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(1, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(2, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(3, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void init3() {
        WindowCards fr_ = frameEditorSolitaire();
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        assertTrue(fr_.getEditorSpider().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorSpider().getCardDialog().getPane()).getTreeAccessible();
        assertEq(25, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(7).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(8).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(9).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(10).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().stackHands().get(11).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(compo(0, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(1, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(2, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(compo(3, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons())));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void deplacer1() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        SolitaireCardsScrollableList stack_ = fr_.getEditorClassic().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> hand_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(hand_.indexOfObj(CardSolitaire.HEART_8),hand_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorClassic().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorClassic().stackHands().get(1).valElts();
        assertEq(2,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(50,stack_.valElts().size());
    }
    @Test
    public void deplacer2() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        SolitaireCardsScrollableList stack_ = fr_.getEditorClassic().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorClassic().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorClassic().stackHands().get(1).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(49,stack_.valElts().size());
    }
    @Test
    public void deplacer3() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        SolitaireCardsScrollableList stack_ = fr_.getEditorClassic().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorClassic().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorClassic().stackHands().get(5).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(49,stack_.valElts().size());
    }
    @Test
    public void deplacer4() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        SolitaireCardsScrollableList stack_ = fr_.getEditorClassic().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorClassic().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorClassic().getEditorCards().getMoveCards());
        assertEq(52,stack_.valElts().size());
        assertFalse(fr_.getEditorClassic().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void deplacer5() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        SolitaireCardsScrollableList stack_ = fr_.getEditorFreeCell().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> hand_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(hand_.indexOfObj(CardSolitaire.HEART_8),hand_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorFreeCell().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorFreeCell().stackHands().get(1).valElts();
        assertEq(2,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(50,stack_.valElts().size());
    }
    @Test
    public void deplacer6() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        SolitaireCardsScrollableList stack_ = fr_.getEditorFreeCell().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorFreeCell().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorFreeCell().stackHands().get(1).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(49,stack_.valElts().size());
    }
    @Test
    public void deplacer7() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        SolitaireCardsScrollableList stack_ = fr_.getEditorFreeCell().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorFreeCell().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorFreeCell().stackHands().get(5).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(49,stack_.valElts().size());
    }
    @Test
    public void deplacer8() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        SolitaireCardsScrollableList stack_ = fr_.getEditorFreeCell().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorFreeCell().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorFreeCell().getEditorCards().getMoveCards());
        assertEq(52,stack_.valElts().size());
        assertFalse(fr_.getEditorFreeCell().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void deplacer9() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        SolitaireCardsScrollableList stack_ = fr_.getEditorSpider().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> hand_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(hand_.indexOfObj(CardSolitaire.HEART_8),hand_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorSpider().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorSpider().stackHands().get(1).valElts();
        assertEq(2,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(102,stack_.valElts().size());
    }
    @Test
    public void deplacer10() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        SolitaireCardsScrollableList stack_ = fr_.getEditorSpider().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorSpider().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorSpider().stackHands().get(1).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(101,stack_.valElts().size());
    }
    @Test
    public void deplacer11() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        SolitaireCardsScrollableList stack_ = fr_.getEditorSpider().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        IdList<CardSolitaire> handFirst_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(CardSolitaire.HEART_8),handFirst_.indexOfObj(CardSolitaire.HEART_1)));
        fr_.getEditorSpider().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        IdList<CardSolitaire> handSecond_ = stack_.valElts();
        selectEventSolitaire(input_, Ints.newList(handSecond_.indexOfObj(CardSolitaire.HEART_10)));
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        IdList<CardSolitaire> result_ = fr_.getEditorSpider().stackHands().get(5).valElts();
        assertEq(3,result_.size());
        assertEq(CardSolitaire.HEART_8,result_.get(0));
        assertEq(CardSolitaire.HEART_1,result_.get(1));
        assertEq(CardSolitaire.HEART_10,result_.get(2));
        assertEq(101,stack_.valElts().size());
    }
    @Test
    public void deplacer12() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        SolitaireCardsScrollableList stack_ = fr_.getEditorSpider().getStack();
        ScrollCustomGraphicList<CardSolitaire> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorSpider().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorSpider().getEditorCards().getMoveCards());
        assertEq(104,stack_.valElts().size());
        assertFalse(fr_.getEditorSpider().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save1() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        dealClassic(fr_);
        fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorClassic().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(12,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_1,CardSolitaire.SPADE_1,CardSolitaire.DIAMOND_1,CardSolitaire.CLUB_1,CardSolitaire.HEART_2,CardSolitaire.SPADE_2,CardSolitaire.DIAMOND_2,CardSolitaire.CLUB_2,CardSolitaire.HEART_3,CardSolitaire.SPADE_3,CardSolitaire.DIAMOND_3,CardSolitaire.CLUB_3,CardSolitaire.HEART_4,CardSolitaire.SPADE_4,CardSolitaire.CLUB_4,CardSolitaire.HEART_5,CardSolitaire.CLUB_5,CardSolitaire.CLUB_6,CardSolitaire.HEART_7,CardSolitaire.DIAMOND_7,CardSolitaire.CLUB_7,CardSolitaire.HEART_8,CardSolitaire.CLUB_8,CardSolitaire.CLUB_9)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_5)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_4)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.CLUB_10)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8,CardSolitaire.SPADE_7)));
        assertTrue(game_.getHandsBegin().get(8).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(9).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(10).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(11).getCards().isEmpty());
    }

    @Test
    public void save2() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        dealClassic(fr_);
        fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertFalse(fr_.getEditorClassic().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(12,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_1,CardSolitaire.SPADE_1,CardSolitaire.DIAMOND_1,CardSolitaire.CLUB_1,CardSolitaire.HEART_2,CardSolitaire.SPADE_2,CardSolitaire.DIAMOND_2,CardSolitaire.CLUB_2,CardSolitaire.HEART_3,CardSolitaire.SPADE_3,CardSolitaire.DIAMOND_3,CardSolitaire.CLUB_3,CardSolitaire.HEART_4,CardSolitaire.SPADE_4,CardSolitaire.CLUB_4,CardSolitaire.HEART_5,CardSolitaire.CLUB_5,CardSolitaire.CLUB_6,CardSolitaire.HEART_7,CardSolitaire.DIAMOND_7,CardSolitaire.CLUB_7,CardSolitaire.HEART_8,CardSolitaire.CLUB_8,CardSolitaire.CLUB_9)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_5)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_4)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.CLUB_10)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8,CardSolitaire.SPADE_7)));
        assertTrue(game_.getHandsBegin().get(8).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(9).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(10).getCards().isEmpty());
        assertTrue(game_.getHandsBegin().get(11).getCards().isEmpty());
    }
    @Test
    public void save3() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorClassic().getCardDialog().isVisible());
        assertFalse(fr_.getEditorClassic().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save4() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        dealClassic(fr_);
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorClassic().getCardDialog().isVisible());
    }
    @Test
    public void save5() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        dealClassic(fr_);
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorClassic().getCardDialog().isVisible());
        fr_.getEditorClassic().backToRules(fr_);
    }
    @Test
    public void save6() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        dealFreeCell(fr_);
        fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorFreeCell().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(16,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8,CardSolitaire.HEART_7)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8,CardSolitaire.SPADE_7)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8,CardSolitaire.DIAMOND_7)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.CLUB_10,CardSolitaire.CLUB_9,CardSolitaire.CLUB_8,CardSolitaire.CLUB_7)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_1,CardSolitaire.HEART_2,CardSolitaire.HEART_3,CardSolitaire.HEART_4,CardSolitaire.HEART_5,CardSolitaire.HEART_6)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_1,CardSolitaire.SPADE_2,CardSolitaire.SPADE_3,CardSolitaire.SPADE_4,CardSolitaire.SPADE_5,CardSolitaire.SPADE_6)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_1,CardSolitaire.DIAMOND_2,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_6)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_1,CardSolitaire.CLUB_2,CardSolitaire.CLUB_3,CardSolitaire.CLUB_4,CardSolitaire.CLUB_5,CardSolitaire.CLUB_6)));
        assertTrue(game_.getHandsBegin().get(8).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(9).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(10).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(11).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(12).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(13).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(14).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(15).getCards().eq(new IdList<CardSolitaire>()));
    }

    @Test
    public void save7() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        dealFreeCell(fr_);
        fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertFalse(fr_.getEditorFreeCell().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(16,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8,CardSolitaire.HEART_7)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8,CardSolitaire.SPADE_7)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8,CardSolitaire.DIAMOND_7)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.CLUB_10,CardSolitaire.CLUB_9,CardSolitaire.CLUB_8,CardSolitaire.CLUB_7)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_1,CardSolitaire.HEART_2,CardSolitaire.HEART_3,CardSolitaire.HEART_4,CardSolitaire.HEART_5,CardSolitaire.HEART_6)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_1,CardSolitaire.SPADE_2,CardSolitaire.SPADE_3,CardSolitaire.SPADE_4,CardSolitaire.SPADE_5,CardSolitaire.SPADE_6)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_1,CardSolitaire.DIAMOND_2,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_6)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_1,CardSolitaire.CLUB_2,CardSolitaire.CLUB_3,CardSolitaire.CLUB_4,CardSolitaire.CLUB_5,CardSolitaire.CLUB_6)));
        assertTrue(game_.getHandsBegin().get(8).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(9).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(10).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(11).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(12).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(13).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(14).getCards().eq(new IdList<CardSolitaire>()));
        assertTrue(game_.getHandsBegin().get(15).getCards().eq(new IdList<CardSolitaire>()));
    }
    @Test
    public void save8() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorFreeCell().getCardDialog().isVisible());
        assertFalse(fr_.getEditorFreeCell().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save9() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        dealFreeCell(fr_);
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorFreeCell().getCardDialog().isVisible());
    }
    @Test
    public void save10() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        dealFreeCell(fr_);
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorFreeCell().getCardDialog().isVisible());
        fr_.getEditorFreeCell().backToRules(fr_);
    }
    @Test
    public void save11() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        dealSpider(fr_);
        fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorSpider().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(12,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_7,CardSolitaire.SPADE_7,CardSolitaire.DIAMOND_7,CardSolitaire.CLUB_7,CardSolitaire.HEART_1,CardSolitaire.SPADE_1,CardSolitaire.DIAMOND_1,CardSolitaire.CLUB_1,CardSolitaire.HEART_JACK,CardSolitaire.SPADE_JACK,CardSolitaire.HEART_1,CardSolitaire.HEART_2,CardSolitaire.HEART_3,CardSolitaire.HEART_4,CardSolitaire.HEART_5,CardSolitaire.SPADE_1,CardSolitaire.SPADE_2,CardSolitaire.SPADE_3,CardSolitaire.SPADE_4,CardSolitaire.SPADE_5,CardSolitaire.DIAMOND_1,CardSolitaire.DIAMOND_2,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_5,CardSolitaire.CLUB_1,CardSolitaire.CLUB_2,CardSolitaire.CLUB_3,CardSolitaire.CLUB_4,CardSolitaire.CLUB_5,CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_7,CardSolitaire.DIAMOND_8,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_10,CardSolitaire.CLUB_6,CardSolitaire.CLUB_7,CardSolitaire.CLUB_8,CardSolitaire.CLUB_9,CardSolitaire.CLUB_10,CardSolitaire.DIAMOND_JACK,CardSolitaire.CLUB_JACK,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_KING)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.CLUB_10,CardSolitaire.CLUB_9,CardSolitaire.CLUB_8)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6,CardSolitaire.HEART_5,CardSolitaire.HEART_4,CardSolitaire.HEART_3,CardSolitaire.HEART_2)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_5,CardSolitaire.SPADE_4,CardSolitaire.SPADE_3,CardSolitaire.SPADE_2)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_2)));
        assertTrue(game_.getHandsBegin().get(8).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_6,CardSolitaire.CLUB_5,CardSolitaire.CLUB_4,CardSolitaire.CLUB_3,CardSolitaire.CLUB_2)));
        assertTrue(game_.getHandsBegin().get(9).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6,CardSolitaire.HEART_7,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8)));
        assertTrue(game_.getHandsBegin().get(10).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_7,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(11).getCards().eq(new IdList<CardSolitaire>()));
    }
    @Test
    public void save12() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        dealSpider(fr_);
        fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertFalse(fr_.getEditorSpider().getCardDialog().isVisible());
        AbsDealSolitaire game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire("/__/txt");
        assertEq(12,game_.getHandsBegin().size());
        assertTrue(game_.getHandsBegin().get(0).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_7,CardSolitaire.SPADE_7,CardSolitaire.DIAMOND_7,CardSolitaire.CLUB_7,CardSolitaire.HEART_1,CardSolitaire.SPADE_1,CardSolitaire.DIAMOND_1,CardSolitaire.CLUB_1,CardSolitaire.HEART_JACK,CardSolitaire.SPADE_JACK,CardSolitaire.HEART_1,CardSolitaire.HEART_2,CardSolitaire.HEART_3,CardSolitaire.HEART_4,CardSolitaire.HEART_5,CardSolitaire.SPADE_1,CardSolitaire.SPADE_2,CardSolitaire.SPADE_3,CardSolitaire.SPADE_4,CardSolitaire.SPADE_5,CardSolitaire.DIAMOND_1,CardSolitaire.DIAMOND_2,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_5,CardSolitaire.CLUB_1,CardSolitaire.CLUB_2,CardSolitaire.CLUB_3,CardSolitaire.CLUB_4,CardSolitaire.CLUB_5,CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_7,CardSolitaire.DIAMOND_8,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_10,CardSolitaire.CLUB_6,CardSolitaire.CLUB_7,CardSolitaire.CLUB_8,CardSolitaire.CLUB_9,CardSolitaire.CLUB_10,CardSolitaire.DIAMOND_JACK,CardSolitaire.CLUB_JACK,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_KING)));
        assertTrue(game_.getHandsBegin().get(1).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_KING,CardSolitaire.HEART_QUEEN,CardSolitaire.HEART_JACK,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8)));
        assertTrue(game_.getHandsBegin().get(2).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_KING,CardSolitaire.SPADE_QUEEN,CardSolitaire.SPADE_JACK,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(3).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_KING,CardSolitaire.DIAMOND_QUEEN,CardSolitaire.DIAMOND_JACK,CardSolitaire.DIAMOND_10,CardSolitaire.DIAMOND_9,CardSolitaire.DIAMOND_8)));
        assertTrue(game_.getHandsBegin().get(4).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_KING,CardSolitaire.CLUB_QUEEN,CardSolitaire.CLUB_JACK,CardSolitaire.CLUB_10,CardSolitaire.CLUB_9,CardSolitaire.CLUB_8)));
        assertTrue(game_.getHandsBegin().get(5).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6,CardSolitaire.HEART_5,CardSolitaire.HEART_4,CardSolitaire.HEART_3,CardSolitaire.HEART_2)));
        assertTrue(game_.getHandsBegin().get(6).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_5,CardSolitaire.SPADE_4,CardSolitaire.SPADE_3,CardSolitaire.SPADE_2)));
        assertTrue(game_.getHandsBegin().get(7).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.DIAMOND_6,CardSolitaire.DIAMOND_5,CardSolitaire.DIAMOND_4,CardSolitaire.DIAMOND_3,CardSolitaire.DIAMOND_2)));
        assertTrue(game_.getHandsBegin().get(8).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.CLUB_6,CardSolitaire.CLUB_5,CardSolitaire.CLUB_4,CardSolitaire.CLUB_3,CardSolitaire.CLUB_2)));
        assertTrue(game_.getHandsBegin().get(9).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.HEART_6,CardSolitaire.HEART_7,CardSolitaire.HEART_10,CardSolitaire.HEART_9,CardSolitaire.HEART_8)));
        assertTrue(game_.getHandsBegin().get(10).getCards().eq(new IdList<CardSolitaire>(CardSolitaire.SPADE_6,CardSolitaire.SPADE_7,CardSolitaire.SPADE_10,CardSolitaire.SPADE_9,CardSolitaire.SPADE_8)));
        assertTrue(game_.getHandsBegin().get(11).getCards().eq(new IdList<CardSolitaire>()));
    }
    @Test
    public void save13() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorSpider().getCardDialog().isVisible());
        assertFalse(fr_.getEditorSpider().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save14() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        dealSpider(fr_);
        tryClick((AbsButton) compo(SAVE_WITHOUT_CLOSING, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorSpider().getCardDialog().isVisible());
    }
    @Test
    public void save15() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        dealSpider(fr_);
        tryClick((AbsButton) compo(SAVE_THEN_CLOSE, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.getEditorSpider().getCardDialog().isVisible());
        fr_.getEditorSpider().backToRules(fr_);
    }

    private AbsCustComponent compo(int _ind, AbsPanel _pan) {
        return ((MockPanel)_pan).getComponent(_ind);
    }

    private void tryMove(WindowCards _fr, SolitaireCardsScrollableList _stack, CardSolitaire _one, int _dest) {
        ScrollCustomGraphicList<CardSolitaire> input_ = _stack.getListe();
        IdList<CardSolitaire> handFirst_ = _stack.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(_one)));
        _fr.getEditorClassic().getEditorCards().getListeTwo().selectItem(_dest);
        tryClick(_fr.getEditorClassic().getEditorCards().getMoveCards());
    }

    private void dealClassic(WindowCards _fr) {
        SolitaireCardsScrollableList stack_ = _fr.getEditorClassic().getStack();
        tryMove(_fr,stack_,CardSolitaire.HEART_6,2);
        tryMove(_fr,stack_,CardSolitaire.SPADE_6,3);
        tryMove(_fr,stack_,CardSolitaire.SPADE_5,3);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_6,4);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_5,4);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_4,4);
        tryMove(_fr,stack_,CardSolitaire.HEART_KING,5);
        tryMove(_fr,stack_,CardSolitaire.SPADE_QUEEN,5);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_JACK,5);
        tryMove(_fr,stack_,CardSolitaire.CLUB_10,5);
        tryMove(_fr,stack_,CardSolitaire.SPADE_KING,6);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_QUEEN,6);
        tryMove(_fr,stack_,CardSolitaire.CLUB_JACK,6);
        tryMove(_fr,stack_,CardSolitaire.HEART_10,6);
        tryMove(_fr,stack_,CardSolitaire.HEART_9,6);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_KING,7);
        tryMove(_fr,stack_,CardSolitaire.CLUB_QUEEN,7);
        tryMove(_fr,stack_,CardSolitaire.HEART_JACK,7);
        tryMove(_fr,stack_,CardSolitaire.SPADE_10,7);
        tryMove(_fr,stack_,CardSolitaire.SPADE_9,7);
        tryMove(_fr,stack_,CardSolitaire.SPADE_8,7);
        tryMove(_fr,stack_,CardSolitaire.CLUB_KING,8);
        tryMove(_fr,stack_,CardSolitaire.HEART_QUEEN,8);
        tryMove(_fr,stack_,CardSolitaire.SPADE_JACK,8);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_10,8);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_9,8);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_8,8);
        tryMove(_fr,stack_,CardSolitaire.SPADE_7,8);
        tryMove(_fr,stack_,CardSolitaire.HEART_1,1);
        tryMove(_fr,stack_,CardSolitaire.SPADE_1,1);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_1,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_1,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_2,1);
        tryMove(_fr,stack_,CardSolitaire.SPADE_2,1);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_2,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_2,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_3,1);
        tryMove(_fr,stack_,CardSolitaire.SPADE_3,1);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_3,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_3,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_4,1);
        tryMove(_fr,stack_,CardSolitaire.SPADE_4,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_4,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_5,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_5,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_6,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_7,1);
        tryMove(_fr,stack_,CardSolitaire.DIAMOND_7,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_7,1);
        tryMove(_fr,stack_,CardSolitaire.HEART_8,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_8,1);
        tryMove(_fr,stack_,CardSolitaire.CLUB_9,1);
    }
    private void tryMoveFreeCell(WindowCards _fr, SolitaireCardsScrollableList _stack, CardSolitaire _one, int _dest) {
        ScrollCustomGraphicList<CardSolitaire> input_ = _stack.getListe();
        IdList<CardSolitaire> handFirst_ = _stack.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(_one)));
        _fr.getEditorFreeCell().getEditorCards().getListeTwo().selectItem(_dest);
        tryClick(_fr.getEditorFreeCell().getEditorCards().getMoveCards());
    }

    private void dealFreeCell(WindowCards _fr) {
        SolitaireCardsScrollableList stack_ = _fr.getEditorFreeCell().getStack();
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_KING,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_QUEEN,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_JACK,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_10,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_9,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_8,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_7,1);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_KING,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_QUEEN,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_JACK,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_10,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_9,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_8,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_7,2);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_KING,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_QUEEN,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_JACK,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_10,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_9,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_8,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_7,3);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_KING,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_QUEEN,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_JACK,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_10,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_9,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_8,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_7,4);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_1,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_2,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_3,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_4,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_5,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.HEART_6,5);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_1,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_2,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_3,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_4,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_5,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.SPADE_6,6);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_1,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_2,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_3,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_4,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_5,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.DIAMOND_6,7);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_1,8);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_2,8);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_3,8);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_4,8);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_5,8);
        tryMoveFreeCell(_fr,stack_,CardSolitaire.CLUB_6,8);
    }

    private void tryMoveSpider(WindowCards _fr, SolitaireCardsScrollableList _stack, CardSolitaire _one, int _dest) {
        ScrollCustomGraphicList<CardSolitaire> input_ = _stack.getListe();
        IdList<CardSolitaire> handFirst_ = _stack.valElts();
        selectEventSolitaire(input_, Ints.newList(handFirst_.indexOfObj(_one)));
        _fr.getEditorSpider().getEditorCards().getListeTwo().selectItem(_dest);
        tryClick(_fr.getEditorSpider().getEditorCards().getMoveCards());
    }
    private void dealSpider(WindowCards _fr) {
        SolitaireCardsScrollableList stack_ = _fr.getEditorSpider().getStack();
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_KING,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_KING,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_KING,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_KING,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_6,6);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_6,7);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_6,8);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_6,9);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_6,10);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_6,11);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_QUEEN,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_QUEEN,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_QUEEN,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_QUEEN,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_5,6);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_5,7);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_5,8);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_5,9);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_7,10);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_7,11);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_JACK,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_JACK,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_JACK,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_JACK,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_4,6);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_4,7);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_4,8);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_4,9);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_10,10);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_10,11);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_10,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_10,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_10,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_10,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_3,6);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_3,7);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_3,8);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_3,9);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_9,10);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_9,11);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_9,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_9,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_9,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_9,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_2,6);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_2,7);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_2,8);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_2,9);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_8,10);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_8,11);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_8,2);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_8,3);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_8,4);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_8,5);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_JACK,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_JACK,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_2,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_3,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_4,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_5,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_2,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_3,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_4,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_5,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_2,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_3,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_4,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_5,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_1,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_2,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_3,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_4,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_5,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_6,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_8,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_9,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_10,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_6,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_7,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_8,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_9,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_10,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_JACK,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_JACK,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_QUEEN,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.HEART_KING,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_QUEEN,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.SPADE_KING,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_QUEEN,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.DIAMOND_KING,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_QUEEN,1);
        tryMoveSpider(_fr,stack_,CardSolitaire.CLUB_KING,1);
    }
}
