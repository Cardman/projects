package cards.gui;

import cards.belote.enumerations.*;
import cards.facade.Games;
import cards.facade.enumerations.*;
import cards.gui.panels.*;
import cards.president.enumerations.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.scripts.messages.cards.MessagesGuiCards;
import code.util.*;
import org.junit.Test;

public final class SavingDealEventTest extends EquallableCardsGuiUtil {
    @Test
    public void belote() {
        WindowCards fr_ = frameMiniBelote("/__/","/_/");
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
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
        tryClick((AbsButton) fr_.getEditorBelote().getEditorCards().getSaveDialogContent().getButtons().getComponent(PLAY_WITHOUT_SAVING));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieBelote());
        assertFalse(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void president() {
        WindowCards fr_ = frameMiniPresident("/__/","/_/");
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
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
        tryClick((AbsButton) fr_.getEditorPresident().getEditorCards().getSaveDialogContent().getButtons().getComponent(PLAY_WITHOUT_SAVING));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartiePresident());
        assertFalse(fr_.getEditorBelote().getCardDialog().isVisible());
    }
    @Test
    public void tarot() {
        WindowCards fr_ = frameMiniTarot("/__/","/_/");
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(EN)), MessagesGuiCards.enGame());
        appendMainGame(Games.getAppliTr(fr_.getFrames().getTranslations().getMapping().getVal(FR)), MessagesGuiCards.frGame());
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
        tryClick((AbsButton) fr_.getEditorTarot().getEditorCards().getSaveDialogContent().getButtons().getComponent(PLAY_WITHOUT_SAVING));
        assertTrue(fr_.baseWindow().getContainerGame().getPar().enCoursDePartieTarot());
        assertFalse(fr_.getEditorTarot().getCardDialog().isVisible());
    }
}
