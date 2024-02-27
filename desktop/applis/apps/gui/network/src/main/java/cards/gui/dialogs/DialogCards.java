package cards.gui.dialogs;

import code.gui.AbsDialog;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

/**
    */

public abstract class DialogCards {

    private final AbstractProgramInfos frames;
    private final AbsCompoFactory compoFactory;
    private final AbsDialog cardDialog;

    protected DialogCards(AbstractProgramInfos _frameFactory) {
        frames = _frameFactory;
        cardDialog = _frameFactory.getFrameFactory().newDialog();
        compoFactory = _frameFactory.getCompoFactory();
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbsDialog getCardDialog() {
        return cardDialog;
    }

    public void closeWindow() {
        cardDialog.closeWindow();
        cardDialog.getPane().removeAll();
    }

}
