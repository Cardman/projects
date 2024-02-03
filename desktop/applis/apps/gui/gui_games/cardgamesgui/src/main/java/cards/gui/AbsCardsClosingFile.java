package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.AbsClosingFile;
import code.threads.AbstractAtomicBoolean;

public abstract class AbsCardsClosingFile implements AbsClosingFile {
    private final AbstractAtomicBoolean modal;

    protected AbsCardsClosingFile(AbstractAtomicBoolean _m) {
        this.modal = _m;
    }

    public void closeFrameFile(AbsCommonFrame _frame) {
        _frame.setVisible(false);
        modal.set(false);
    }
}
