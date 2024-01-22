package cards.gui;

import cards.facade.enumerations.GameEnum;
import cards.gui.panels.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.DocumentReaderTarotUtil;
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

public final class EditorTarotTest extends EquallableCardsGuiUtil {
    @Test
    public void init() {
        WindowCards fr_ = frameEditorTarot();
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        assertTrue(fr_.getEditorTarot().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.TRUMP)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.SUIT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.POINT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.CHARACTER)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.TAKE)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.GUARD_WITHOUT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.GUARD_AGAINST)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.SLAM)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getAllowPlayCalledSuit()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getDiscardAfterCall()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbAtoutsPoignee()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixThree().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixFour().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixFive().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBoutonPoignees()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbGames()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getValidateRules()));
        assertTrue(fr_.getEditorTarot().getDiscardAfterCall().isSelected());
        assertTrue(fr_.getEditorTarot().getAllowPlayCalledSuit().isSelected());
    }
    @Test
    public void validate1() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
//        fr_.getEditorTarot().getNbStacks().setValue(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        assertEq(78, fr_.getEditorTarot().getStack().getListe().size());
        CustList<TarotCardsScrollableList> handsStack_ = fr_.getEditorTarot().stackHands();
        assertEq(7,handsStack_.size());
        assertEq(15, handsStack_.get(1).getMax());
        assertEq(15, handsStack_.get(2).getMax());
        assertEq(15, handsStack_.get(3).getMax());
        assertEq(15, handsStack_.get(4).getMax());
        assertEq(15, handsStack_.get(5).getMax());
        assertEq(3, handsStack_.get(6).getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        assertTrue(tr_.containsObj(handsStack_.get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void validate2() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(4);
//        fr_.getEditorTarot().getNbStacks().setValue(4);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        assertEq(78, fr_.getEditorTarot().getStack().getListe().size());
        CustList<TarotCardsScrollableList> handsStack_ = fr_.getEditorTarot().stackHands();
        assertEq(6,handsStack_.size());
        assertEq(18, handsStack_.get(1).getMax());
        assertEq(18, handsStack_.get(2).getMax());
        assertEq(18, handsStack_.get(3).getMax());
        assertEq(18, handsStack_.get(4).getMax());
        assertEq(6, handsStack_.get(5).getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        assertTrue(tr_.containsObj(handsStack_.get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getTypedString()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getSearch()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
        eventsCombo(fr_.getEditorTarot().getListeChoixFour().getCombo(),0);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        assertEq(78, fr_.getEditorTarot().getStack().getListe().size());
        CustList<TarotCardsScrollableList> handsStack_ = fr_.getEditorTarot().stackHands();
        assertEq(7,handsStack_.size());
        assertEq(14, handsStack_.get(1).getMax());
        assertEq(14, handsStack_.get(2).getMax());
        assertEq(14, handsStack_.get(3).getMax());
        assertEq(14, handsStack_.get(4).getMax());
        assertEq(14, handsStack_.get(5).getMax());
        assertEq(8, handsStack_.get(6).getMax());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(19, tr_.size());
        assertTrue(tr_.containsObj(handsStack_.get(0).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(1).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(2).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(3).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(4).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(5).getListe().getGlobal()));
        assertTrue(tr_.containsObj(handsStack_.get(6).getListe().getGlobal()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListe().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getListeTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getMoveCards()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getBackToRules()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(0)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(1)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(2)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(3)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileTable()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFolderSystem()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getErrors()));
    }
    @Test
    public void deplacer1() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
//        fr_.getEditorTarot().getNbStacks().setValue(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> hand_ = stack_.valMain();
        selectEvent(input_, Ints.newList(hand_.indexOfObj(CardTarot.HEART_1),hand_.indexOfObj(CardTarot.HEART_8)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> result_ = fr_.getEditorTarot().stackHands().get(1).valMain();
        assertEq(2,result_.size());
        assertEq(CardTarot.HEART_8,result_.get(0));
        assertEq(CardTarot.HEART_1,result_.get(1));
        assertEq(76,stack_.valMain().size());
    }
    @Test
    public void deplacer2() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.HEART_1),handFirst_.indexOfObj(CardTarot.HEART_10)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.HEART_8)));
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> result_ = fr_.getEditorTarot().stackHands().get(1).valMain();
        assertEq(3,result_.size());
        assertEq(CardTarot.HEART_10,result_.get(0));
        assertEq(CardTarot.HEART_8,result_.get(1));
        assertEq(CardTarot.HEART_1,result_.get(2));
        assertEq(75,stack_.valMain().size());
    }
    @Test
    public void deplacer3() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.HEART_1),handFirst_.indexOfObj(CardTarot.HEART_8)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.HEART_10)));
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> result_ = fr_.getEditorTarot().stackHands().get(5).valMain();
        assertEq(3,result_.size());
        assertEq(CardTarot.HEART_10,result_.get(0));
        assertEq(CardTarot.HEART_8,result_.get(1));
        assertEq(CardTarot.HEART_1,result_.get(2));
        assertEq(75,stack_.valMain().size());
    }
    @Test
    public void deplacer4() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        assertEq(78,stack_.valMain().size());
        assertFalse(fr_.getEditorTarot().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save1() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.EXCUSE),handFirst_.indexOfObj(CardTarot.TRUMP_21),handFirst_.indexOfObj(CardTarot.TRUMP_20),handFirst_.indexOfObj(CardTarot.TRUMP_19),handFirst_.indexOfObj(CardTarot.TRUMP_18),handFirst_.indexOfObj(CardTarot.TRUMP_17),handFirst_.indexOfObj(CardTarot.TRUMP_16),handFirst_.indexOfObj(CardTarot.TRUMP_15),handFirst_.indexOfObj(CardTarot.TRUMP_14),handFirst_.indexOfObj(CardTarot.TRUMP_13),handFirst_.indexOfObj(CardTarot.TRUMP_12),handFirst_.indexOfObj(CardTarot.TRUMP_11)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.TRUMP_10),handSecond_.indexOfObj(CardTarot.TRUMP_9),handSecond_.indexOfObj(CardTarot.TRUMP_8),handSecond_.indexOfObj(CardTarot.TRUMP_7),handSecond_.indexOfObj(CardTarot.TRUMP_6),handSecond_.indexOfObj(CardTarot.TRUMP_5),handSecond_.indexOfObj(CardTarot.TRUMP_4),handSecond_.indexOfObj(CardTarot.TRUMP_3),handSecond_.indexOfObj(CardTarot.TRUMP_2),handSecond_.indexOfObj(CardTarot.TRUMP_1),handSecond_.indexOfObj(CardTarot.HEART_KING),handSecond_.indexOfObj(CardTarot.HEART_QUEEN)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardTarot.HEART_KNIGHT),handThird_.indexOfObj(CardTarot.HEART_JACK),handThird_.indexOfObj(CardTarot.HEART_10),handThird_.indexOfObj(CardTarot.HEART_9),handThird_.indexOfObj(CardTarot.HEART_8),handThird_.indexOfObj(CardTarot.HEART_7),handThird_.indexOfObj(CardTarot.HEART_6),handThird_.indexOfObj(CardTarot.HEART_5),handThird_.indexOfObj(CardTarot.HEART_4),handThird_.indexOfObj(CardTarot.HEART_3),handThird_.indexOfObj(CardTarot.HEART_2),handThird_.indexOfObj(CardTarot.HEART_1)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardTarot.SPADE_KING),handFour_.indexOfObj(CardTarot.SPADE_QUEEN),handFour_.indexOfObj(CardTarot.SPADE_KNIGHT),handFour_.indexOfObj(CardTarot.SPADE_JACK),handFour_.indexOfObj(CardTarot.SPADE_10),handFour_.indexOfObj(CardTarot.SPADE_9),handFour_.indexOfObj(CardTarot.SPADE_8),handFour_.indexOfObj(CardTarot.SPADE_7),handFour_.indexOfObj(CardTarot.SPADE_6),handFour_.indexOfObj(CardTarot.SPADE_5),handFour_.indexOfObj(CardTarot.SPADE_4),handFour_.indexOfObj(CardTarot.SPADE_3)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFive_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFive_.indexOfObj(CardTarot.SPADE_2),handFive_.indexOfObj(CardTarot.SPADE_1),handFive_.indexOfObj(CardTarot.DIAMOND_KING),handFive_.indexOfObj(CardTarot.DIAMOND_QUEEN),handFive_.indexOfObj(CardTarot.DIAMOND_KNIGHT),handFive_.indexOfObj(CardTarot.DIAMOND_JACK),handFive_.indexOfObj(CardTarot.DIAMOND_10),handFive_.indexOfObj(CardTarot.DIAMOND_9),handFive_.indexOfObj(CardTarot.DIAMOND_8),handFive_.indexOfObj(CardTarot.DIAMOND_7),handFive_.indexOfObj(CardTarot.DIAMOND_6),handFive_.indexOfObj(CardTarot.DIAMOND_5)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSix_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSix_.indexOfObj(CardTarot.DIAMOND_4),handSix_.indexOfObj(CardTarot.DIAMOND_3),handSix_.indexOfObj(CardTarot.DIAMOND_2),handSix_.indexOfObj(CardTarot.DIAMOND_1),handSix_.indexOfObj(CardTarot.CLUB_KING),handSix_.indexOfObj(CardTarot.CLUB_QUEEN),handSix_.indexOfObj(CardTarot.CLUB_KNIGHT),handSix_.indexOfObj(CardTarot.CLUB_JACK),handSix_.indexOfObj(CardTarot.CLUB_10),handSix_.indexOfObj(CardTarot.CLUB_9),handSix_.indexOfObj(CardTarot.CLUB_8),handSix_.indexOfObj(CardTarot.CLUB_7)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(7);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        fr_.getEditorTarot().getEditorCards().getListe().selectItem(1);
        fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorTarot().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GameTarot game_ = DocumentReaderTarotUtil.getGameTarot(doc_);
        assertEq(1,game_.getDeal().getDealer());
        assertEq(7,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(twelve(CardTarot.EXCUSE,CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_12,CardTarot.TRUMP_11)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(twelve(CardTarot.TRUMP_10,CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_5,CardTarot.TRUMP_4,CardTarot.TRUMP_3,CardTarot.TRUMP_2,CardTarot.TRUMP_1,CardTarot.HEART_KING,CardTarot.HEART_QUEEN)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(twelve(CardTarot.HEART_KNIGHT,CardTarot.HEART_JACK,CardTarot.HEART_10,CardTarot.HEART_9,CardTarot.HEART_8,CardTarot.HEART_7,CardTarot.HEART_6,CardTarot.HEART_5,CardTarot.HEART_4,CardTarot.HEART_3,CardTarot.HEART_2,CardTarot.HEART_1)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(twelve(CardTarot.SPADE_KING,CardTarot.SPADE_QUEEN,CardTarot.SPADE_KNIGHT,CardTarot.SPADE_JACK,CardTarot.SPADE_10,CardTarot.SPADE_9,CardTarot.SPADE_8,CardTarot.SPADE_7,CardTarot.SPADE_6,CardTarot.SPADE_5,CardTarot.SPADE_4,CardTarot.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardTarot.SPADE_2,CardTarot.SPADE_1,CardTarot.DIAMOND_KING,CardTarot.DIAMOND_QUEEN,CardTarot.DIAMOND_KNIGHT,CardTarot.DIAMOND_JACK,CardTarot.DIAMOND_10,CardTarot.DIAMOND_9,CardTarot.DIAMOND_8,CardTarot.DIAMOND_7,CardTarot.DIAMOND_6,CardTarot.DIAMOND_5)));
        assertTrue(game_.getDeal().getDeal().get(5).getCards().eq(twelve(CardTarot.DIAMOND_4,CardTarot.DIAMOND_3,CardTarot.DIAMOND_2,CardTarot.DIAMOND_1,CardTarot.CLUB_KING,CardTarot.CLUB_QUEEN,CardTarot.CLUB_KNIGHT,CardTarot.CLUB_JACK,CardTarot.CLUB_10,CardTarot.CLUB_9,CardTarot.CLUB_8,CardTarot.CLUB_7)));
        assertTrue(game_.getDeal().getDeal().get(6).getCards().eq(six(CardTarot.CLUB_6,CardTarot.CLUB_5,CardTarot.CLUB_4,CardTarot.CLUB_3,CardTarot.CLUB_2,CardTarot.CLUB_1)));
    }
    @Test
    public void save2() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/",dbs(0.25,0.75,0.75,0.75,0.75));
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.EXCUSE),handFirst_.indexOfObj(CardTarot.TRUMP_21),handFirst_.indexOfObj(CardTarot.TRUMP_20),handFirst_.indexOfObj(CardTarot.TRUMP_19),handFirst_.indexOfObj(CardTarot.TRUMP_18),handFirst_.indexOfObj(CardTarot.TRUMP_17),handFirst_.indexOfObj(CardTarot.TRUMP_16),handFirst_.indexOfObj(CardTarot.TRUMP_15),handFirst_.indexOfObj(CardTarot.TRUMP_14),handFirst_.indexOfObj(CardTarot.TRUMP_13),handFirst_.indexOfObj(CardTarot.TRUMP_12),handFirst_.indexOfObj(CardTarot.TRUMP_11)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.TRUMP_10),handSecond_.indexOfObj(CardTarot.TRUMP_9),handSecond_.indexOfObj(CardTarot.TRUMP_8),handSecond_.indexOfObj(CardTarot.TRUMP_7),handSecond_.indexOfObj(CardTarot.TRUMP_6),handSecond_.indexOfObj(CardTarot.TRUMP_5),handSecond_.indexOfObj(CardTarot.TRUMP_4),handSecond_.indexOfObj(CardTarot.TRUMP_3),handSecond_.indexOfObj(CardTarot.TRUMP_2),handSecond_.indexOfObj(CardTarot.TRUMP_1),handSecond_.indexOfObj(CardTarot.HEART_KING),handSecond_.indexOfObj(CardTarot.HEART_QUEEN)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardTarot.HEART_KNIGHT),handThird_.indexOfObj(CardTarot.HEART_JACK),handThird_.indexOfObj(CardTarot.HEART_10),handThird_.indexOfObj(CardTarot.HEART_9),handThird_.indexOfObj(CardTarot.HEART_8),handThird_.indexOfObj(CardTarot.HEART_7),handThird_.indexOfObj(CardTarot.HEART_6),handThird_.indexOfObj(CardTarot.HEART_5),handThird_.indexOfObj(CardTarot.HEART_4),handThird_.indexOfObj(CardTarot.HEART_3),handThird_.indexOfObj(CardTarot.HEART_2),handThird_.indexOfObj(CardTarot.HEART_1)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardTarot.SPADE_KING),handFour_.indexOfObj(CardTarot.SPADE_QUEEN),handFour_.indexOfObj(CardTarot.SPADE_KNIGHT),handFour_.indexOfObj(CardTarot.SPADE_JACK),handFour_.indexOfObj(CardTarot.SPADE_10),handFour_.indexOfObj(CardTarot.SPADE_9),handFour_.indexOfObj(CardTarot.SPADE_8),handFour_.indexOfObj(CardTarot.SPADE_7),handFour_.indexOfObj(CardTarot.SPADE_6),handFour_.indexOfObj(CardTarot.SPADE_5),handFour_.indexOfObj(CardTarot.SPADE_4),handFour_.indexOfObj(CardTarot.SPADE_3)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFive_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFive_.indexOfObj(CardTarot.SPADE_2),handFive_.indexOfObj(CardTarot.SPADE_1),handFive_.indexOfObj(CardTarot.DIAMOND_KING),handFive_.indexOfObj(CardTarot.DIAMOND_QUEEN),handFive_.indexOfObj(CardTarot.DIAMOND_KNIGHT),handFive_.indexOfObj(CardTarot.DIAMOND_JACK),handFive_.indexOfObj(CardTarot.DIAMOND_10),handFive_.indexOfObj(CardTarot.DIAMOND_9),handFive_.indexOfObj(CardTarot.DIAMOND_8),handFive_.indexOfObj(CardTarot.DIAMOND_7),handFive_.indexOfObj(CardTarot.DIAMOND_6),handFive_.indexOfObj(CardTarot.DIAMOND_5)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSix_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSix_.indexOfObj(CardTarot.DIAMOND_4),handSix_.indexOfObj(CardTarot.DIAMOND_3),handSix_.indexOfObj(CardTarot.DIAMOND_2),handSix_.indexOfObj(CardTarot.DIAMOND_1),handSix_.indexOfObj(CardTarot.CLUB_KING),handSix_.indexOfObj(CardTarot.CLUB_QUEEN),handSix_.indexOfObj(CardTarot.CLUB_KNIGHT),handSix_.indexOfObj(CardTarot.CLUB_JACK),handSix_.indexOfObj(CardTarot.CLUB_10),handSix_.indexOfObj(CardTarot.CLUB_9),handSix_.indexOfObj(CardTarot.CLUB_8),handSix_.indexOfObj(CardTarot.CLUB_7)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(7);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        fr_.getEditorTarot().getEditorCards().getListe().selectItem(6);
        fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorTarot().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GameTarot game_ = DocumentReaderTarotUtil.getGameTarot(doc_);
        assertEq(1,game_.getDeal().getDealer());
        assertEq(7,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(twelve(CardTarot.EXCUSE,CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_12,CardTarot.TRUMP_11)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(twelve(CardTarot.TRUMP_10,CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_5,CardTarot.TRUMP_4,CardTarot.TRUMP_3,CardTarot.TRUMP_2,CardTarot.TRUMP_1,CardTarot.HEART_KING,CardTarot.HEART_QUEEN)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(twelve(CardTarot.HEART_KNIGHT,CardTarot.HEART_JACK,CardTarot.HEART_10,CardTarot.HEART_9,CardTarot.HEART_8,CardTarot.HEART_7,CardTarot.HEART_6,CardTarot.HEART_5,CardTarot.HEART_4,CardTarot.HEART_3,CardTarot.HEART_2,CardTarot.HEART_1)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(twelve(CardTarot.SPADE_KING,CardTarot.SPADE_QUEEN,CardTarot.SPADE_KNIGHT,CardTarot.SPADE_JACK,CardTarot.SPADE_10,CardTarot.SPADE_9,CardTarot.SPADE_8,CardTarot.SPADE_7,CardTarot.SPADE_6,CardTarot.SPADE_5,CardTarot.SPADE_4,CardTarot.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardTarot.SPADE_2,CardTarot.SPADE_1,CardTarot.DIAMOND_KING,CardTarot.DIAMOND_QUEEN,CardTarot.DIAMOND_KNIGHT,CardTarot.DIAMOND_JACK,CardTarot.DIAMOND_10,CardTarot.DIAMOND_9,CardTarot.DIAMOND_8,CardTarot.DIAMOND_7,CardTarot.DIAMOND_6,CardTarot.DIAMOND_5)));
        assertTrue(game_.getDeal().getDeal().get(5).getCards().eq(twelve(CardTarot.DIAMOND_4,CardTarot.DIAMOND_3,CardTarot.DIAMOND_2,CardTarot.DIAMOND_1,CardTarot.CLUB_KING,CardTarot.CLUB_QUEEN,CardTarot.CLUB_KNIGHT,CardTarot.CLUB_JACK,CardTarot.CLUB_10,CardTarot.CLUB_9,CardTarot.CLUB_8,CardTarot.CLUB_7)));
        assertTrue(game_.getDeal().getDeal().get(6).getCards().eq(six(CardTarot.CLUB_6,CardTarot.CLUB_5,CardTarot.CLUB_4,CardTarot.CLUB_3,CardTarot.CLUB_2,CardTarot.CLUB_1)));
    }
    @Test
    public void save3() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.EXCUSE),handFirst_.indexOfObj(CardTarot.TRUMP_21),handFirst_.indexOfObj(CardTarot.TRUMP_20),handFirst_.indexOfObj(CardTarot.TRUMP_19),handFirst_.indexOfObj(CardTarot.TRUMP_18),handFirst_.indexOfObj(CardTarot.TRUMP_17),handFirst_.indexOfObj(CardTarot.TRUMP_16),handFirst_.indexOfObj(CardTarot.TRUMP_15),handFirst_.indexOfObj(CardTarot.TRUMP_14),handFirst_.indexOfObj(CardTarot.TRUMP_13),handFirst_.indexOfObj(CardTarot.TRUMP_12),handFirst_.indexOfObj(CardTarot.TRUMP_11)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.TRUMP_10),handSecond_.indexOfObj(CardTarot.TRUMP_9),handSecond_.indexOfObj(CardTarot.TRUMP_8),handSecond_.indexOfObj(CardTarot.TRUMP_7),handSecond_.indexOfObj(CardTarot.TRUMP_6),handSecond_.indexOfObj(CardTarot.TRUMP_5),handSecond_.indexOfObj(CardTarot.TRUMP_4),handSecond_.indexOfObj(CardTarot.TRUMP_3),handSecond_.indexOfObj(CardTarot.TRUMP_2),handSecond_.indexOfObj(CardTarot.TRUMP_1),handSecond_.indexOfObj(CardTarot.HEART_KING),handSecond_.indexOfObj(CardTarot.HEART_QUEEN)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handThird_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardTarot.HEART_KNIGHT),handThird_.indexOfObj(CardTarot.HEART_JACK),handThird_.indexOfObj(CardTarot.HEART_10),handThird_.indexOfObj(CardTarot.HEART_9),handThird_.indexOfObj(CardTarot.HEART_8),handThird_.indexOfObj(CardTarot.HEART_7),handThird_.indexOfObj(CardTarot.HEART_6),handThird_.indexOfObj(CardTarot.HEART_5),handThird_.indexOfObj(CardTarot.HEART_4),handThird_.indexOfObj(CardTarot.HEART_3),handThird_.indexOfObj(CardTarot.HEART_2),handThird_.indexOfObj(CardTarot.HEART_1)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFour_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardTarot.SPADE_KING),handFour_.indexOfObj(CardTarot.SPADE_QUEEN),handFour_.indexOfObj(CardTarot.SPADE_KNIGHT),handFour_.indexOfObj(CardTarot.SPADE_JACK),handFour_.indexOfObj(CardTarot.SPADE_10),handFour_.indexOfObj(CardTarot.SPADE_9),handFour_.indexOfObj(CardTarot.SPADE_8),handFour_.indexOfObj(CardTarot.SPADE_7),handFour_.indexOfObj(CardTarot.SPADE_6),handFour_.indexOfObj(CardTarot.SPADE_5),handFour_.indexOfObj(CardTarot.SPADE_4),handFour_.indexOfObj(CardTarot.SPADE_3)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFive_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handFive_.indexOfObj(CardTarot.SPADE_2),handFive_.indexOfObj(CardTarot.SPADE_1),handFive_.indexOfObj(CardTarot.DIAMOND_KING),handFive_.indexOfObj(CardTarot.DIAMOND_QUEEN),handFive_.indexOfObj(CardTarot.DIAMOND_KNIGHT),handFive_.indexOfObj(CardTarot.DIAMOND_JACK),handFive_.indexOfObj(CardTarot.DIAMOND_10),handFive_.indexOfObj(CardTarot.DIAMOND_9),handFive_.indexOfObj(CardTarot.DIAMOND_8),handFive_.indexOfObj(CardTarot.DIAMOND_7),handFive_.indexOfObj(CardTarot.DIAMOND_6),handFive_.indexOfObj(CardTarot.DIAMOND_5)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSix_ = stack_.valMain();
        selectEvent(input_, Ints.newList(handSix_.indexOfObj(CardTarot.DIAMOND_4),handSix_.indexOfObj(CardTarot.DIAMOND_3),handSix_.indexOfObj(CardTarot.DIAMOND_2),handSix_.indexOfObj(CardTarot.DIAMOND_1),handSix_.indexOfObj(CardTarot.CLUB_KING),handSix_.indexOfObj(CardTarot.CLUB_QUEEN),handSix_.indexOfObj(CardTarot.CLUB_KNIGHT),handSix_.indexOfObj(CardTarot.CLUB_JACK),handSix_.indexOfObj(CardTarot.CLUB_10),handSix_.indexOfObj(CardTarot.CLUB_9),handSix_.indexOfObj(CardTarot.CLUB_8),handSix_.indexOfObj(CardTarot.CLUB_7)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(7);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        fr_.getEditorTarot().getEditorCards().getListe().selectItem(1);
        fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_THEN_CLOSE));
        assertFalse(fr_.getEditorTarot().getCardDialog().isVisible());
        String content_ = StreamTextFile.contentsOfFile("/__/txt", fr_.getFileCoreStream(), fr_.getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        GameTarot game_ = DocumentReaderTarotUtil.getGameTarot(doc_);
        assertEq(1,game_.getDeal().getDealer());
        assertEq(7,game_.getDeal().getDeal().size());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(twelve(CardTarot.EXCUSE,CardTarot.TRUMP_21,CardTarot.TRUMP_20,CardTarot.TRUMP_19,CardTarot.TRUMP_18,CardTarot.TRUMP_17,CardTarot.TRUMP_16,CardTarot.TRUMP_15,CardTarot.TRUMP_14,CardTarot.TRUMP_13,CardTarot.TRUMP_12,CardTarot.TRUMP_11)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(twelve(CardTarot.TRUMP_10,CardTarot.TRUMP_9,CardTarot.TRUMP_8,CardTarot.TRUMP_7,CardTarot.TRUMP_6,CardTarot.TRUMP_5,CardTarot.TRUMP_4,CardTarot.TRUMP_3,CardTarot.TRUMP_2,CardTarot.TRUMP_1,CardTarot.HEART_KING,CardTarot.HEART_QUEEN)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(twelve(CardTarot.HEART_KNIGHT,CardTarot.HEART_JACK,CardTarot.HEART_10,CardTarot.HEART_9,CardTarot.HEART_8,CardTarot.HEART_7,CardTarot.HEART_6,CardTarot.HEART_5,CardTarot.HEART_4,CardTarot.HEART_3,CardTarot.HEART_2,CardTarot.HEART_1)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(twelve(CardTarot.SPADE_KING,CardTarot.SPADE_QUEEN,CardTarot.SPADE_KNIGHT,CardTarot.SPADE_JACK,CardTarot.SPADE_10,CardTarot.SPADE_9,CardTarot.SPADE_8,CardTarot.SPADE_7,CardTarot.SPADE_6,CardTarot.SPADE_5,CardTarot.SPADE_4,CardTarot.SPADE_3)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardTarot.SPADE_2,CardTarot.SPADE_1,CardTarot.DIAMOND_KING,CardTarot.DIAMOND_QUEEN,CardTarot.DIAMOND_KNIGHT,CardTarot.DIAMOND_JACK,CardTarot.DIAMOND_10,CardTarot.DIAMOND_9,CardTarot.DIAMOND_8,CardTarot.DIAMOND_7,CardTarot.DIAMOND_6,CardTarot.DIAMOND_5)));
        assertTrue(game_.getDeal().getDeal().get(5).getCards().eq(twelve(CardTarot.DIAMOND_4,CardTarot.DIAMOND_3,CardTarot.DIAMOND_2,CardTarot.DIAMOND_1,CardTarot.CLUB_KING,CardTarot.CLUB_QUEEN,CardTarot.CLUB_KNIGHT,CardTarot.CLUB_JACK,CardTarot.CLUB_10,CardTarot.CLUB_9,CardTarot.CLUB_8,CardTarot.CLUB_7)));
        assertTrue(game_.getDeal().getDeal().get(6).getCards().eq(six(CardTarot.CLUB_6,CardTarot.CLUB_5,CardTarot.CLUB_4,CardTarot.CLUB_3,CardTarot.CLUB_2,CardTarot.CLUB_1)));
    }
    @Test
    public void save4() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorTarot().getCardDialog().isVisible());
        assertFalse(fr_.getEditorTarot().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void back() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        tryClick(fr_.getEditorTarot().getEditorCards().getBackToRules());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getEditorTarot().getCardDialog().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.TRUMP)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.SUIT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.LOW_CARDS)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.POINT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getMiseres().getVal(Miseres.CHARACTER)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.TAKE)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.GUARD_WITHOUT)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.GUARD_AGAINST)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBids().getVal(BidTarot.SLAM)));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getAllowPlayCalledSuit()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getDiscardAfterCall()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbAtoutsPoignee()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoix().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixTwo().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixThree().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixFour().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getListeChoixFive().self()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbJoueurs()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getBoutonPoignees()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getNbGames()));
        assertTrue(tr_.containsObj(fr_.getEditorTarot().getEditorCards().getValidateRules()));
        assertTrue(fr_.getEditorTarot().getDiscardAfterCall().isSelected());
        assertTrue(fr_.getEditorTarot().getAllowPlayCalledSuit().isSelected());
    }
    private IdList<CardTarot> six(CardTarot _one, CardTarot _two, CardTarot _three, CardTarot _four, CardTarot _five, CardTarot _six){
        IdList<CardTarot> l_ = new IdList<CardTarot>();
        l_.add(_one);
        l_.add(_two);
        l_.add(_three);
        l_.add(_four);
        l_.add(_five);
        l_.add(_six);
        return l_;
    }
    private IdList<CardTarot> twelve(CardTarot _one, CardTarot _two, CardTarot _three, CardTarot _four, CardTarot _five, CardTarot _six, CardTarot _seven, CardTarot _eight, CardTarot _nine, CardTarot _ten, CardTarot _eleven, CardTarot _twelve){
        IdList<CardTarot> l_ = new IdList<CardTarot>();
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
        l_.add(_twelve);
        return l_;
    }
    private void selectEvent(ScrollCustomGraphicList<CardTarot> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
}
