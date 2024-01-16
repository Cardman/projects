package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ClosingEditorCards;
import code.gui.AbsDialog;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

/**
    */

abstract class DialogCards {

    private final AbsCompoFactory compoFactory;
    private WindowCardsInt main;
    private final AbsDialog cardDialog;
    private final ClosingEditorCards clos;
    private boolean validated;

    protected DialogCards(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        if (_ch != null) {
            clos = _ch;
            cardDialog = _frameFactory.getFrameFactory().newDialog(_ch);
        } else {
            clos = new ClosingEditorCards();
            cardDialog = _frameFactory.getFrameFactory().newDialog();
        }
        compoFactory = _frameFactory.getCompoFactory();
    }

    public static MixCardsChoice[] allMixCardsChoice() {
        return new MixCardsChoice[]{MixCardsChoice.EACH_DEAL,MixCardsChoice.EACH_LAUNCHING,MixCardsChoice.ONCE_ONLY,MixCardsChoice.NEVER};
    }

    public ClosingEditorCards getClos() {
        return clos;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbsDialog getCardDialog() {
        return cardDialog;
    }

    public void setMain(WindowCardsInt _main) {
        main = _main;
    }
    public WindowCardsInt getMain() {
        return main;
    }
    public void closeWindow() {
        cardDialog.closeWindow();
        cardDialog.getPane().removeAll();
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean _v) {
        this.validated = _v;
    }
}
