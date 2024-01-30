package cards.gui;

import cards.belote.GameBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.panels.BeloteCardsScrollableList;
import code.gui.AbsButton;
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
        assertFalse(fr_.getEditorBelote().getUnderTrumpingFoe().isSelected());
        assertTrue(fr_.getEditorBelote().getClassic().isSelected());
        assertEq(1,fr_.getEditorBelote().getNbGames().getValue());
        assertEq(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,fr_.getEditorBelote().getListChoiceTwo().getCurrentElement());
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
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
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
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryToggle(fr_.getEditorBelote().getDealAll());
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        assertEq(0, fr_.getEditorBelote().getRemaining().getMax());
    }
    @Test
    public void validate3() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
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
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> hand_ = stack_.valElts();
        selectEvent(input_, Ints.newList(hand_.indexOfObj(CardBelote.HEART_1),hand_.indexOfObj(CardBelote.HEART_8)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(1).valElts();
        assertEq(2,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_8,result_.get(1));
        assertEq(30,stack_.valElts().size());
    }
    @Test
    public void deplacer2() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_8)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.HEART_10)));
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(1).valElts();
        assertEq(3,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_10,result_.get(1));
        assertEq(CardBelote.HEART_8,result_.get(2));
        assertEq(29,stack_.valElts().size());
    }
    @Test
    public void deplacer3() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_8)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.HEART_10)));
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> result_ = fr_.getEditorBelote().stackHands().get(5).valElts();
        assertEq(3,result_.size());
        assertEq(CardBelote.HEART_1,result_.get(0));
        assertEq(CardBelote.HEART_8,result_.get(1));
        assertEq(CardBelote.HEART_10,result_.get(2));
        assertEq(29,stack_.valElts().size());
    }
    @Test
    public void deplacer4() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        fr_.getCore().getFacadeCards().getParametres().setSaveHomeFolder(false);
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        assertEq(32,stack_.valElts().size());
        assertFalse(fr_.getEditorBelote().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save1() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(1);
        fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
        GameBelote game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote("/__/txt");
        assertEq(1,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertEq(5,game_.getDeal().getDeal().get(0).total());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(five(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_10,CardBelote.HEART_KING)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(five(CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10,CardBelote.SPADE_KING)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(five(CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_9,CardBelote.DIAMOND_1,CardBelote.DIAMOND_10,CardBelote.DIAMOND_KING)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(five(CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_KING)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardBelote.HEART_QUEEN,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_QUEEN,CardBelote.CLUB_8,CardBelote.CLUB_7)));
    }
    @Test
    public void save2() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/",dbs(0.375,0.75,0.75,0.75,0.75));
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(4);
        fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
        GameBelote game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote("/__/txt");
        assertEq(1,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertEq(5,game_.getDeal().getDeal().get(0).total());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(five(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_10,CardBelote.HEART_KING)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(five(CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10,CardBelote.SPADE_KING)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(five(CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_9,CardBelote.DIAMOND_1,CardBelote.DIAMOND_10,CardBelote.DIAMOND_KING)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(five(CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_KING)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardBelote.HEART_QUEEN,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_QUEEN,CardBelote.CLUB_8,CardBelote.CLUB_7)));
    }
    @Test
    public void save3() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(1);
        fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_THEN_CLOSE));
        assertFalse(fr_.getEditorBelote().getCardDialog().isVisible());
        GameBelote game_ = fr_.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().belote("/__/txt");
        assertEq(1,game_.getDeal().getDealer());
        assertEq(5,game_.getDeal().getDeal().size());
        assertEq(5,game_.getDeal().getDeal().get(0).total());
        assertTrue(game_.getDeal().getDeal().get(0).getCards().eq(five(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_1,CardBelote.HEART_10,CardBelote.HEART_KING)));
        assertTrue(game_.getDeal().getDeal().get(1).getCards().eq(five(CardBelote.SPADE_JACK,CardBelote.SPADE_9,CardBelote.SPADE_1,CardBelote.SPADE_10,CardBelote.SPADE_KING)));
        assertTrue(game_.getDeal().getDeal().get(2).getCards().eq(five(CardBelote.DIAMOND_JACK,CardBelote.DIAMOND_9,CardBelote.DIAMOND_1,CardBelote.DIAMOND_10,CardBelote.DIAMOND_KING)));
        assertTrue(game_.getDeal().getDeal().get(3).getCards().eq(five(CardBelote.CLUB_JACK,CardBelote.CLUB_9,CardBelote.CLUB_1,CardBelote.CLUB_10,CardBelote.CLUB_KING)));
        assertTrue(game_.getDeal().getDeal().get(4).getCards().eq(twelve(CardBelote.HEART_QUEEN,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.SPADE_QUEEN,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.CLUB_QUEEN,CardBelote.CLUB_8,CardBelote.CLUB_7)));
    }
    @Test
    public void save4() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
        assertFalse(fr_.getEditorBelote().getEditorCards().getErrors().getText().isEmpty());
    }
    @Test
    public void save5() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(1);
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_WITHOUT_CLOSING));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void save6() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEvent(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(1);
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(SAVE_THEN_CLOSE));
        assertTrue(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void back() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        tryClick(fr_.getEditorBelote().getEditorCards().getBackToRules());
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
        assertFalse(fr_.getEditorBelote().getUnderTrumpingFoe().isSelected());
        assertTrue(fr_.getEditorBelote().getClassic().isSelected());
        assertEq(1,fr_.getEditorBelote().getNbGames().getValue());
        assertEq(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,fr_.getEditorBelote().getListChoiceTwo().getCurrentElement());
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
    private IdList<CardBelote> five(CardBelote _one,CardBelote _two,CardBelote _three,CardBelote _four,CardBelote _five){
        IdList<CardBelote> l_ = new IdList<CardBelote>();
        l_.add(_one);
        l_.add(_two);
        l_.add(_three);
        l_.add(_four);
        l_.add(_five);
        return l_;
    }
    private IdList<CardBelote> twelve(CardBelote _one,CardBelote _two,CardBelote _three,CardBelote _four,CardBelote _five,CardBelote _six,CardBelote _seven,CardBelote _eight,CardBelote _nine,CardBelote _ten,CardBelote _eleven, CardBelote _twelve){
        IdList<CardBelote> l_ = new IdList<CardBelote>();
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
    private void selectEvent(ScrollCustomGraphicList<CardBelote> _input, Ints _indices) {
        _input.select(_indices);
        _input.events();
    }
}
