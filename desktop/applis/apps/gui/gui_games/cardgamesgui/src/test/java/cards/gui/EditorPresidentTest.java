package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.panels.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.president.sml.DocumentReaderPresidentUtil;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.ScrollCustomGraphicList;
import code.mock.MockCustComponent;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import org.junit.Test;

public final class EditorPresidentTest extends EquallableCardsGuiUtil {
    @Test
    public void init() {
        WindowCards fr_ = frameEditorPresident();
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        assertTrue(fr_.getEditorPresident().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(11, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbGames()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getLooseFinishBestCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getPossibleReversing()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getCanPass()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEquality().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getLooserStartsFirst()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getSwitchCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbStacks()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getValidateRules()));
        assertFalse(fr_.getEditorPresident().getPossibleReversing().isSelected());
        assertTrue(fr_.getEditorPresident().getCanPass().isSelected());
        assertTrue(fr_.getEditorPresident().getLooserStartsFirst().isSelected());
        assertTrue(fr_.getEditorPresident().getLooseFinishBestCards().isSelected());
        assertTrue(fr_.getEditorPresident().getSwitchCards().isSelected());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        assertEq(104, fr_.getEditorPresident().getStack().getListe().size());
        CustList<PresidentCardsScrollableList> handsStack_ = fr_.getEditorPresident().stackHands();
        assertEq(6,handsStack_.size());
        assertEq(21, handsStack_.get(1).getMax());
        assertEq(21, handsStack_.get(2).getMax());
        assertEq(21, handsStack_.get(3).getMax());
        assertEq(21, handsStack_.get(4).getMax());
        assertEq(21, handsStack_.get(5).getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        assertTrue(tr_.containsObj(handsStack_.get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(8);
        fr_.getEditorPresident().getNbStacks().setValue(4);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        assertEq(208, fr_.getEditorPresident().getStack().getListe().size());
        CustList<PresidentCardsScrollableList> handsStack_ = fr_.getEditorPresident().stackHands();
        assertEq(9,handsStack_.size());
        assertEq(26, handsStack_.get(1).getMax());
        assertEq(26, handsStack_.get(2).getMax());
        assertEq(26, handsStack_.get(3).getMax());
        assertEq(26, handsStack_.get(4).getMax());
        assertEq(26, handsStack_.get(5).getMax());
        assertEq(26, handsStack_.get(6).getMax());
        assertEq(26, handsStack_.get(7).getMax());
        assertEq(26, handsStack_.get(8).getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(23, tr_.size());
        assertTrue(tr_.containsObj(handsStack_.get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(7).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(8).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(18, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().stackHands().get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void deplacer1() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> hand_ = stack_.valMain();
        selectEvent(input_, Ints.newList(hand_.indexOfObj(CardPresident.HEART_1),hand_.indexOfObj(CardPresident.HEART_8)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> result_ = fr_.getEditorPresident().stackHands().get(1).valMain();
        assertEq(2,result_.size());
        assertEq(CardPresident.HEART_1,result_.get(0));
        assertEq(CardPresident.HEART_8,result_.get(1));
        assertEq(102,stack_.valMain().size());
    }
    @Test
    public void deplacer2() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_8)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_10)));
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> result_ = fr_.getEditorPresident().stackHands().get(1).valMain();
        assertEq(3,result_.size());
        assertEq(CardPresident.HEART_1,result_.get(0));
        assertEq(CardPresident.HEART_10,result_.get(1));
        assertEq(CardPresident.HEART_8,result_.get(2));
        assertEq(101,stack_.valMain().size());
    }
    @Test
    public void deplacer3() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_8),handFirst_.indexOfObj(CardPresident.HEART_8,handFirst_.indexOfObj(CardPresident.HEART_8)+1)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_10)));
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> result_ = fr_.getEditorPresident().stackHands().get(5).valMain();
        assertEq(4,result_.size());
        assertEq(CardPresident.HEART_1,result_.get(0));
        assertEq(CardPresident.HEART_10,result_.get(1));
        assertEq(CardPresident.HEART_8,result_.get(2));
        assertEq(CardPresident.HEART_8,result_.get(3));
        assertEq(100,stack_.valMain().size());
    }
    @Test
    public void deplacer4() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        fr_.getEditorPresident().getNbStacks().setValue(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        assertEq(104,stack_.valMain().size());
        assertFalse(fr_.getEditorPresident().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save1() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_2),handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_KING),handFirst_.indexOfObj(CardPresident.HEART_QUEEN),handFirst_.indexOfObj(CardPresident.HEART_JACK),handFirst_.indexOfObj(CardPresident.HEART_10),handFirst_.indexOfObj(CardPresident.HEART_9),handFirst_.indexOfObj(CardPresident.HEART_8),handFirst_.indexOfObj(CardPresident.HEART_7),handFirst_.indexOfObj(CardPresident.HEART_6),handFirst_.indexOfObj(CardPresident.HEART_5)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_4),handSecond_.indexOfObj(CardPresident.HEART_3),handSecond_.indexOfObj(CardPresident.SPADE_2),handSecond_.indexOfObj(CardPresident.SPADE_1),handSecond_.indexOfObj(CardPresident.SPADE_KING),handSecond_.indexOfObj(CardPresident.SPADE_QUEEN),handSecond_.indexOfObj(CardPresident.SPADE_JACK),handSecond_.indexOfObj(CardPresident.SPADE_10),handSecond_.indexOfObj(CardPresident.SPADE_9),handSecond_.indexOfObj(CardPresident.SPADE_8),handSecond_.indexOfObj(CardPresident.SPADE_7)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardPresident.SPADE_6),handThird_.indexOfObj(CardPresident.SPADE_5),handThird_.indexOfObj(CardPresident.SPADE_4),handThird_.indexOfObj(CardPresident.SPADE_3),handThird_.indexOfObj(CardPresident.DIAMOND_2),handThird_.indexOfObj(CardPresident.DIAMOND_1),handThird_.indexOfObj(CardPresident.DIAMOND_KING),handThird_.indexOfObj(CardPresident.DIAMOND_QUEEN),handThird_.indexOfObj(CardPresident.DIAMOND_JACK),handThird_.indexOfObj(CardPresident.DIAMOND_10)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardPresident.DIAMOND_9),handFour_.indexOfObj(CardPresident.DIAMOND_8),handFour_.indexOfObj(CardPresident.DIAMOND_7),handFour_.indexOfObj(CardPresident.DIAMOND_6),handFour_.indexOfObj(CardPresident.DIAMOND_5),handFour_.indexOfObj(CardPresident.DIAMOND_4),handFour_.indexOfObj(CardPresident.DIAMOND_3),handFour_.indexOfObj(CardPresident.CLUB_2),handFour_.indexOfObj(CardPresident.CLUB_1),handFour_.indexOfObj(CardPresident.CLUB_KING)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        fr_.getEditorPresident().getEditorCards().getListe().selectItem(1);
        fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorPresident().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GamePresident game_ = DocumentReaderPresidentUtil.getGamePresident(doc_);
        assertEq(1,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(eleven(CardPresident.HEART_2,CardPresident.HEART_1,CardPresident.HEART_KING,CardPresident.HEART_QUEEN,CardPresident.HEART_JACK,CardPresident.HEART_10,CardPresident.HEART_9,CardPresident.HEART_8,CardPresident.HEART_7,CardPresident.HEART_6,CardPresident.HEART_5)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(eleven(CardPresident.SPADE_2,CardPresident.SPADE_1,CardPresident.SPADE_KING,CardPresident.SPADE_QUEEN,CardPresident.SPADE_JACK,CardPresident.SPADE_10,CardPresident.SPADE_9,CardPresident.SPADE_8,CardPresident.SPADE_7,CardPresident.HEART_4,CardPresident.HEART_3)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(ten(CardPresident.DIAMOND_2,CardPresident.DIAMOND_1,CardPresident.DIAMOND_KING,CardPresident.DIAMOND_QUEEN,CardPresident.DIAMOND_JACK,CardPresident.DIAMOND_10,CardPresident.SPADE_6,CardPresident.SPADE_5,CardPresident.SPADE_4,CardPresident.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(ten(CardPresident.CLUB_2,CardPresident.CLUB_1,CardPresident.CLUB_KING,CardPresident.DIAMOND_9,CardPresident.DIAMOND_8,CardPresident.DIAMOND_7,CardPresident.DIAMOND_6,CardPresident.DIAMOND_5,CardPresident.DIAMOND_4,CardPresident.DIAMOND_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(ten(CardPresident.CLUB_QUEEN,CardPresident.CLUB_JACK,CardPresident.CLUB_10,CardPresident.CLUB_9,CardPresident.CLUB_8,CardPresident.CLUB_7,CardPresident.CLUB_6,CardPresident.CLUB_5,CardPresident.CLUB_4,CardPresident.CLUB_3)));
    }
    @Test
    public void save2() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/",dbs(0.4375,0.75,0.75,0.75,0.75));
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_2),handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_KING),handFirst_.indexOfObj(CardPresident.HEART_QUEEN),handFirst_.indexOfObj(CardPresident.HEART_JACK),handFirst_.indexOfObj(CardPresident.HEART_10),handFirst_.indexOfObj(CardPresident.HEART_9),handFirst_.indexOfObj(CardPresident.HEART_8),handFirst_.indexOfObj(CardPresident.HEART_7),handFirst_.indexOfObj(CardPresident.HEART_6),handFirst_.indexOfObj(CardPresident.HEART_5)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_4),handSecond_.indexOfObj(CardPresident.HEART_3),handSecond_.indexOfObj(CardPresident.SPADE_2),handSecond_.indexOfObj(CardPresident.SPADE_1),handSecond_.indexOfObj(CardPresident.SPADE_KING),handSecond_.indexOfObj(CardPresident.SPADE_QUEEN),handSecond_.indexOfObj(CardPresident.SPADE_JACK),handSecond_.indexOfObj(CardPresident.SPADE_10),handSecond_.indexOfObj(CardPresident.SPADE_9),handSecond_.indexOfObj(CardPresident.SPADE_8),handSecond_.indexOfObj(CardPresident.SPADE_7)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardPresident.SPADE_6),handThird_.indexOfObj(CardPresident.SPADE_5),handThird_.indexOfObj(CardPresident.SPADE_4),handThird_.indexOfObj(CardPresident.SPADE_3),handThird_.indexOfObj(CardPresident.DIAMOND_2),handThird_.indexOfObj(CardPresident.DIAMOND_1),handThird_.indexOfObj(CardPresident.DIAMOND_KING),handThird_.indexOfObj(CardPresident.DIAMOND_QUEEN),handThird_.indexOfObj(CardPresident.DIAMOND_JACK),handThird_.indexOfObj(CardPresident.DIAMOND_10)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardPresident.DIAMOND_9),handFour_.indexOfObj(CardPresident.DIAMOND_8),handFour_.indexOfObj(CardPresident.DIAMOND_7),handFour_.indexOfObj(CardPresident.DIAMOND_6),handFour_.indexOfObj(CardPresident.DIAMOND_5),handFour_.indexOfObj(CardPresident.DIAMOND_4),handFour_.indexOfObj(CardPresident.DIAMOND_3),handFour_.indexOfObj(CardPresident.CLUB_2),handFour_.indexOfObj(CardPresident.CLUB_1),handFour_.indexOfObj(CardPresident.CLUB_KING)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        fr_.getEditorPresident().getEditorCards().getListe().selectItem(5);
        fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorPresident().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GamePresident game_ = DocumentReaderPresidentUtil.getGamePresident(doc_);
        assertEq(2,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(eleven(CardPresident.HEART_2,CardPresident.HEART_1,CardPresident.HEART_KING,CardPresident.HEART_QUEEN,CardPresident.HEART_JACK,CardPresident.HEART_10,CardPresident.HEART_9,CardPresident.HEART_8,CardPresident.HEART_7,CardPresident.HEART_6,CardPresident.HEART_5)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(eleven(CardPresident.SPADE_2,CardPresident.SPADE_1,CardPresident.SPADE_KING,CardPresident.SPADE_QUEEN,CardPresident.SPADE_JACK,CardPresident.SPADE_10,CardPresident.SPADE_9,CardPresident.SPADE_8,CardPresident.SPADE_7,CardPresident.HEART_4,CardPresident.HEART_3)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(ten(CardPresident.DIAMOND_2,CardPresident.DIAMOND_1,CardPresident.DIAMOND_KING,CardPresident.DIAMOND_QUEEN,CardPresident.DIAMOND_JACK,CardPresident.DIAMOND_10,CardPresident.SPADE_6,CardPresident.SPADE_5,CardPresident.SPADE_4,CardPresident.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(ten(CardPresident.CLUB_2,CardPresident.CLUB_1,CardPresident.CLUB_KING,CardPresident.DIAMOND_9,CardPresident.DIAMOND_8,CardPresident.DIAMOND_7,CardPresident.DIAMOND_6,CardPresident.DIAMOND_5,CardPresident.DIAMOND_4,CardPresident.DIAMOND_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(ten(CardPresident.CLUB_QUEEN,CardPresident.CLUB_JACK,CardPresident.CLUB_10,CardPresident.CLUB_9,CardPresident.CLUB_8,CardPresident.CLUB_7,CardPresident.CLUB_6,CardPresident.CLUB_5,CardPresident.CLUB_4,CardPresident.CLUB_3)));
    }
    @Test
    public void save3() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_2),handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_KING),handFirst_.indexOfObj(CardPresident.HEART_QUEEN),handFirst_.indexOfObj(CardPresident.HEART_JACK),handFirst_.indexOfObj(CardPresident.HEART_10),handFirst_.indexOfObj(CardPresident.HEART_9),handFirst_.indexOfObj(CardPresident.HEART_8),handFirst_.indexOfObj(CardPresident.HEART_7),handFirst_.indexOfObj(CardPresident.HEART_6),handFirst_.indexOfObj(CardPresident.HEART_5)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_4),handSecond_.indexOfObj(CardPresident.HEART_3),handSecond_.indexOfObj(CardPresident.SPADE_2),handSecond_.indexOfObj(CardPresident.SPADE_1),handSecond_.indexOfObj(CardPresident.SPADE_KING),handSecond_.indexOfObj(CardPresident.SPADE_QUEEN),handSecond_.indexOfObj(CardPresident.SPADE_JACK),handSecond_.indexOfObj(CardPresident.SPADE_10),handSecond_.indexOfObj(CardPresident.SPADE_9),handSecond_.indexOfObj(CardPresident.SPADE_8),handSecond_.indexOfObj(CardPresident.SPADE_7)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardPresident.SPADE_6),handThird_.indexOfObj(CardPresident.SPADE_5),handThird_.indexOfObj(CardPresident.SPADE_4),handThird_.indexOfObj(CardPresident.SPADE_3),handThird_.indexOfObj(CardPresident.DIAMOND_2),handThird_.indexOfObj(CardPresident.DIAMOND_1),handThird_.indexOfObj(CardPresident.DIAMOND_KING),handThird_.indexOfObj(CardPresident.DIAMOND_QUEEN),handThird_.indexOfObj(CardPresident.DIAMOND_JACK),handThird_.indexOfObj(CardPresident.DIAMOND_10)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardPresident.DIAMOND_9),handFour_.indexOfObj(CardPresident.DIAMOND_8),handFour_.indexOfObj(CardPresident.DIAMOND_7),handFour_.indexOfObj(CardPresident.DIAMOND_6),handFour_.indexOfObj(CardPresident.DIAMOND_5),handFour_.indexOfObj(CardPresident.DIAMOND_4),handFour_.indexOfObj(CardPresident.DIAMOND_3),handFour_.indexOfObj(CardPresident.CLUB_2),handFour_.indexOfObj(CardPresident.CLUB_1),handFour_.indexOfObj(CardPresident.CLUB_KING)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        fr_.getEditorPresident().getEditorCards().getListe().selectItem(1);
        fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_THEN_CLOSE));
        assertFalse(fr_.getEditorPresident().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GamePresident game_ = DocumentReaderPresidentUtil.getGamePresident(doc_);
        assertEq(1,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(eleven(CardPresident.HEART_2,CardPresident.HEART_1,CardPresident.HEART_KING,CardPresident.HEART_QUEEN,CardPresident.HEART_JACK,CardPresident.HEART_10,CardPresident.HEART_9,CardPresident.HEART_8,CardPresident.HEART_7,CardPresident.HEART_6,CardPresident.HEART_5)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(eleven(CardPresident.SPADE_2,CardPresident.SPADE_1,CardPresident.SPADE_KING,CardPresident.SPADE_QUEEN,CardPresident.SPADE_JACK,CardPresident.SPADE_10,CardPresident.SPADE_9,CardPresident.SPADE_8,CardPresident.SPADE_7,CardPresident.HEART_4,CardPresident.HEART_3)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(ten(CardPresident.DIAMOND_2,CardPresident.DIAMOND_1,CardPresident.DIAMOND_KING,CardPresident.DIAMOND_QUEEN,CardPresident.DIAMOND_JACK,CardPresident.DIAMOND_10,CardPresident.SPADE_6,CardPresident.SPADE_5,CardPresident.SPADE_4,CardPresident.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(ten(CardPresident.CLUB_2,CardPresident.CLUB_1,CardPresident.CLUB_KING,CardPresident.DIAMOND_9,CardPresident.DIAMOND_8,CardPresident.DIAMOND_7,CardPresident.DIAMOND_6,CardPresident.DIAMOND_5,CardPresident.DIAMOND_4,CardPresident.DIAMOND_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(ten(CardPresident.CLUB_QUEEN,CardPresident.CLUB_JACK,CardPresident.CLUB_10,CardPresident.CLUB_9,CardPresident.CLUB_8,CardPresident.CLUB_7,CardPresident.CLUB_6,CardPresident.CLUB_5,CardPresident.CLUB_4,CardPresident.CLUB_3)));
    }
    @Test
    public void save4() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorPresident().getCardDialog().isVisible());
        assertFalse(fr_.getEditorPresident().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void back() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        tryClick(fr_.getEditorPresident().getEditorCards().getBackToRules());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorPresident().getCardDialog().getPane()).getTreeAccessible();
        assertEq(11, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbGames()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getLooseFinishBestCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getPossibleReversing()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getCanPass()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEquality().self()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getLooserStartsFirst()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getSwitchCards()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getNbStacks()));
        assertTrue(tr_.containsObj(fr_.getEditorPresident().getEditorCards().getValidateRules()));
        assertFalse(fr_.getEditorPresident().getPossibleReversing().isSelected());
        assertTrue(fr_.getEditorPresident().getCanPass().isSelected());
        assertTrue(fr_.getEditorPresident().getLooserStartsFirst().isSelected());
        assertTrue(fr_.getEditorPresident().getLooseFinishBestCards().isSelected());
        assertTrue(fr_.getEditorPresident().getSwitchCards().isSelected());
    }
    private IdList<CardPresident> ten(CardPresident _one,CardPresident _two,CardPresident _three,CardPresident _four,CardPresident _five,CardPresident _six,CardPresident _seven,CardPresident _eight,CardPresident _nine,CardPresident _ten){
        IdList<CardPresident> l_ = new IdList<CardPresident>();
        l_.add(_one);
        l_.add(_two);
        l_.add(_three);
        l_.add(_four);
        l_.add(_five);
        l_.add(_six);
        l_.add(_seven);
        l_.add(_eight);
        l_.add(_nine);
        l_.add(_ten);
        return l_;
    }
    private IdList<CardPresident> eleven(CardPresident _one,CardPresident _two,CardPresident _three,CardPresident _four,CardPresident _five,CardPresident _six,CardPresident _seven,CardPresident _eight,CardPresident _nine,CardPresident _ten,CardPresident _eleven){
        IdList<CardPresident> l_ = new IdList<CardPresident>();
        l_.add(_one);
        l_.add(_two);
        l_.add(_three);
        l_.add(_four);
        l_.add(_five);
        l_.add(_six);
        l_.add(_seven);
        l_.add(_eight);
        l_.add(_nine);
        l_.add(_ten);
        l_.add(_eleven);
        return l_;
    }
    private void selectEvent(ScrollCustomGraphicList<CardPresident> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
}
