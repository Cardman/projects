package cards.gui;

import cards.belote.enumerations.*;
import cards.facade.MessagesCardGames;
import cards.facade.enumerations.*;
import cards.gui.panels.*;
import cards.president.enumerations.*;
import cards.solitaire.CardSolitaire;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.mock.MockPanel;
import code.scripts.messages.cards.MessagesGuiCards;
import code.util.*;
import org.junit.Test;

public final class SavingDealEventTest extends EquallableCardsGuiUtil {
    @Test
    public void belote() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.BELOTE));
        tryClick(fr_.getEditorBelote().getEditorCards().getValidateRules());
        BeloteCardsScrollableList stack_ = fr_.getEditorBelote().getStack();
        ScrollCustomGraphicList<CardBelote> input_ = stack_.getListe();
        IdList<CardBelote> handFirst_ = stack_.valElts();
        selectEventBelote(input_, Ints.newList(handFirst_.indexOfObj(CardBelote.HEART_JACK),handFirst_.indexOfObj(CardBelote.HEART_9),handFirst_.indexOfObj(CardBelote.HEART_1),handFirst_.indexOfObj(CardBelote.HEART_10),handFirst_.indexOfObj(CardBelote.HEART_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handSecond_ = stack_.valElts();
        selectEventBelote(input_, Ints.newList(handSecond_.indexOfObj(CardBelote.SPADE_JACK),handSecond_.indexOfObj(CardBelote.SPADE_9),handSecond_.indexOfObj(CardBelote.SPADE_1),handSecond_.indexOfObj(CardBelote.SPADE_10),handSecond_.indexOfObj(CardBelote.SPADE_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handThird_ = stack_.valElts();
        selectEventBelote(input_, Ints.newList(handThird_.indexOfObj(CardBelote.DIAMOND_JACK),handThird_.indexOfObj(CardBelote.DIAMOND_9),handThird_.indexOfObj(CardBelote.DIAMOND_1),handThird_.indexOfObj(CardBelote.DIAMOND_10),handThird_.indexOfObj(CardBelote.DIAMOND_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        IdList<CardBelote> handFour_ = stack_.valElts();
        selectEventBelote(input_, Ints.newList(handFour_.indexOfObj(CardBelote.CLUB_JACK),handFour_.indexOfObj(CardBelote.CLUB_9),handFour_.indexOfObj(CardBelote.CLUB_1),handFour_.indexOfObj(CardBelote.CLUB_10),handFour_.indexOfObj(CardBelote.CLUB_KING)));
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorBelote().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorBelote().getEditorCards().getMoveCards());
        fr_.getEditorBelote().getEditorCards().getListe().selectItem(1);
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieBelote());
        assertFalse(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void president() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.PRESIDENT));
        fr_.getEditorPresident().getNbJoueurs().setValue(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getValidateRules());
        PresidentCardsScrollableList stack_ = fr_.getEditorPresident().getStack();
        ScrollCustomGraphicList<CardPresident> input_ = stack_.getListe();
        IdList<CardPresident> handFirst_ = stack_.valElts();
        selectEventPresident(input_, Ints.newList(handFirst_.indexOfObj(CardPresident.HEART_2),handFirst_.indexOfObj(CardPresident.HEART_1),handFirst_.indexOfObj(CardPresident.HEART_KING),handFirst_.indexOfObj(CardPresident.HEART_QUEEN),handFirst_.indexOfObj(CardPresident.HEART_JACK),handFirst_.indexOfObj(CardPresident.HEART_10),handFirst_.indexOfObj(CardPresident.HEART_9),handFirst_.indexOfObj(CardPresident.HEART_8),handFirst_.indexOfObj(CardPresident.HEART_7),handFirst_.indexOfObj(CardPresident.HEART_6),handFirst_.indexOfObj(CardPresident.HEART_5)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handSecond_ = stack_.valElts();
        selectEventPresident(input_, Ints.newList(handSecond_.indexOfObj(CardPresident.HEART_4),handSecond_.indexOfObj(CardPresident.HEART_3),handSecond_.indexOfObj(CardPresident.SPADE_2),handSecond_.indexOfObj(CardPresident.SPADE_1),handSecond_.indexOfObj(CardPresident.SPADE_KING),handSecond_.indexOfObj(CardPresident.SPADE_QUEEN),handSecond_.indexOfObj(CardPresident.SPADE_JACK),handSecond_.indexOfObj(CardPresident.SPADE_10),handSecond_.indexOfObj(CardPresident.SPADE_9),handSecond_.indexOfObj(CardPresident.SPADE_8),handSecond_.indexOfObj(CardPresident.SPADE_7)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handThird_ = stack_.valElts();
        selectEventPresident(input_, Ints.newList(handThird_.indexOfObj(CardPresident.SPADE_6),handThird_.indexOfObj(CardPresident.SPADE_5),handThird_.indexOfObj(CardPresident.SPADE_4),handThird_.indexOfObj(CardPresident.SPADE_3),handThird_.indexOfObj(CardPresident.DIAMOND_2),handThird_.indexOfObj(CardPresident.DIAMOND_1),handThird_.indexOfObj(CardPresident.DIAMOND_KING),handThird_.indexOfObj(CardPresident.DIAMOND_QUEEN),handThird_.indexOfObj(CardPresident.DIAMOND_JACK),handThird_.indexOfObj(CardPresident.DIAMOND_10)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        IdList<CardPresident> handFour_ = stack_.valElts();
        selectEventPresident(input_, Ints.newList(handFour_.indexOfObj(CardPresident.DIAMOND_9),handFour_.indexOfObj(CardPresident.DIAMOND_8),handFour_.indexOfObj(CardPresident.DIAMOND_7),handFour_.indexOfObj(CardPresident.DIAMOND_6),handFour_.indexOfObj(CardPresident.DIAMOND_5),handFour_.indexOfObj(CardPresident.DIAMOND_4),handFour_.indexOfObj(CardPresident.DIAMOND_3),handFour_.indexOfObj(CardPresident.CLUB_2),handFour_.indexOfObj(CardPresident.CLUB_1),handFour_.indexOfObj(CardPresident.CLUB_KING)));
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorPresident().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorPresident().getEditorCards().getMoveCards());
        fr_.getEditorPresident().getEditorCards().getListe().selectItem(1);
        fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartiePresident());
        assertFalse(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void tarot() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.TAROT));
        fr_.getEditorTarot().getNbJoueurs().setValue(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getValidateRules());
        TarotCardsScrollableList stack_ = fr_.getEditorTarot().getStack();
        ScrollCustomGraphicList<CardTarot> input_ = stack_.getListe();
        IdList<CardTarot> handFirst_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handFirst_.indexOfObj(CardTarot.EXCUSE),handFirst_.indexOfObj(CardTarot.TRUMP_21),handFirst_.indexOfObj(CardTarot.TRUMP_20),handFirst_.indexOfObj(CardTarot.TRUMP_19),handFirst_.indexOfObj(CardTarot.TRUMP_18),handFirst_.indexOfObj(CardTarot.TRUMP_17),handFirst_.indexOfObj(CardTarot.TRUMP_16),handFirst_.indexOfObj(CardTarot.TRUMP_15),handFirst_.indexOfObj(CardTarot.TRUMP_14),handFirst_.indexOfObj(CardTarot.TRUMP_13),handFirst_.indexOfObj(CardTarot.TRUMP_12),handFirst_.indexOfObj(CardTarot.TRUMP_11)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(1);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSecond_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handSecond_.indexOfObj(CardTarot.TRUMP_10),handSecond_.indexOfObj(CardTarot.TRUMP_9),handSecond_.indexOfObj(CardTarot.TRUMP_8),handSecond_.indexOfObj(CardTarot.TRUMP_7),handSecond_.indexOfObj(CardTarot.TRUMP_6),handSecond_.indexOfObj(CardTarot.TRUMP_5),handSecond_.indexOfObj(CardTarot.TRUMP_4),handSecond_.indexOfObj(CardTarot.TRUMP_3),handSecond_.indexOfObj(CardTarot.TRUMP_2),handSecond_.indexOfObj(CardTarot.TRUMP_1),handSecond_.indexOfObj(CardTarot.HEART_KING),handSecond_.indexOfObj(CardTarot.HEART_QUEEN)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(2);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handThird_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handThird_.indexOfObj(CardTarot.HEART_KNIGHT),handThird_.indexOfObj(CardTarot.HEART_JACK),handThird_.indexOfObj(CardTarot.HEART_10),handThird_.indexOfObj(CardTarot.HEART_9),handThird_.indexOfObj(CardTarot.HEART_8),handThird_.indexOfObj(CardTarot.HEART_7),handThird_.indexOfObj(CardTarot.HEART_6),handThird_.indexOfObj(CardTarot.HEART_5),handThird_.indexOfObj(CardTarot.HEART_4),handThird_.indexOfObj(CardTarot.HEART_3),handThird_.indexOfObj(CardTarot.HEART_2),handThird_.indexOfObj(CardTarot.HEART_1)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(3);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFour_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handFour_.indexOfObj(CardTarot.SPADE_KING),handFour_.indexOfObj(CardTarot.SPADE_QUEEN),handFour_.indexOfObj(CardTarot.SPADE_KNIGHT),handFour_.indexOfObj(CardTarot.SPADE_JACK),handFour_.indexOfObj(CardTarot.SPADE_10),handFour_.indexOfObj(CardTarot.SPADE_9),handFour_.indexOfObj(CardTarot.SPADE_8),handFour_.indexOfObj(CardTarot.SPADE_7),handFour_.indexOfObj(CardTarot.SPADE_6),handFour_.indexOfObj(CardTarot.SPADE_5),handFour_.indexOfObj(CardTarot.SPADE_4),handFour_.indexOfObj(CardTarot.SPADE_3)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(4);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handFive_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handFive_.indexOfObj(CardTarot.SPADE_2),handFive_.indexOfObj(CardTarot.SPADE_1),handFive_.indexOfObj(CardTarot.DIAMOND_KING),handFive_.indexOfObj(CardTarot.DIAMOND_QUEEN),handFive_.indexOfObj(CardTarot.DIAMOND_KNIGHT),handFive_.indexOfObj(CardTarot.DIAMOND_JACK),handFive_.indexOfObj(CardTarot.DIAMOND_10),handFive_.indexOfObj(CardTarot.DIAMOND_9),handFive_.indexOfObj(CardTarot.DIAMOND_8),handFive_.indexOfObj(CardTarot.DIAMOND_7),handFive_.indexOfObj(CardTarot.DIAMOND_6),handFive_.indexOfObj(CardTarot.DIAMOND_5)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(5);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        IdList<CardTarot> handSix_ = stack_.valElts();
        selectEventTarot(input_, Ints.newList(handSix_.indexOfObj(CardTarot.DIAMOND_4),handSix_.indexOfObj(CardTarot.DIAMOND_3),handSix_.indexOfObj(CardTarot.DIAMOND_2),handSix_.indexOfObj(CardTarot.DIAMOND_1),handSix_.indexOfObj(CardTarot.CLUB_KING),handSix_.indexOfObj(CardTarot.CLUB_QUEEN),handSix_.indexOfObj(CardTarot.CLUB_KNIGHT),handSix_.indexOfObj(CardTarot.CLUB_JACK),handSix_.indexOfObj(CardTarot.CLUB_10),handSix_.indexOfObj(CardTarot.CLUB_9),handSix_.indexOfObj(CardTarot.CLUB_8),handSix_.indexOfObj(CardTarot.CLUB_7)));
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(6);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        input_.selectAll();
        fr_.getEditorTarot().getEditorCards().getListeTwo().selectItem(7);
        tryClick(fr_.getEditorTarot().getEditorCards().getMoveCards());
        fr_.getEditorTarot().getEditorCards().getListe().selectItem(1);
        fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieTarot());
        assertFalse(fr_.getEditorTarot().getCardDialog().isVisible());
    }
    @Test
    public void solitaireClassic() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.CLASSIC));
        dealClassic(fr_);
        fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorClassic().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieSolitaire());
        assertFalse(fr_.getEditorClassic().getCardDialog().isVisible());
    }
    @Test
    public void solitaireFreeCell() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.FREECELL));
        dealFreeCell(fr_);
        fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorFreeCell().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieSolitaire());
        assertFalse(fr_.getEditorFreeCell().getCardDialog().isVisible());
    }
    @Test
    public void solitaireSpider() {
        WindowCards fr_ = frameMiniSolitaire("/__/","/_/");
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(MessagesCardGames.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
        tryClick(fr_.getEditGames().getVal(GameEnum.SPIDER));
        dealSpider(fr_);
        fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getFileName().setText("txt");
        tryClick((AbsButton) compo(PLAY_WITHOUT_SAVING, fr_.getEditorSpider().getEditorCards().getSaveDialogContent().getButtons()));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieSolitaire());
        assertFalse(fr_.getEditorSpider().getCardDialog().isVisible());
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
