package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.AbsClosingFile;
import code.threads.AbstractAtomicBoolean;

public final class CardsClosingFile implements AbsClosingFile {
    private final AbstractAtomicBoolean modal;

    public CardsClosingFile(AbstractAtomicBoolean _m) {
        this.modal = _m;
    }

    @Override
    public void closeFrameFile(AbsCommonFrame _frame) {
        _frame.setVisible(false);
        modal.set(false);
    }
}
