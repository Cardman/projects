package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.AbsClosingFile;
import code.gui.files.FileDialogContent;
import code.threads.AbstractAtomicBoolean;

public final class CardsClosingFile implements AbsClosingFile {
    private final AbstractAtomicBoolean modal;

    public CardsClosingFile(AbstractAtomicBoolean _m) {
        this.modal = _m;
    }

    @Override
    public void closeFrameFile(AbsCommonFrame _frame, FileDialogContent _content) {
        _frame.setVisible(false);
        modal.set(false);
    }
}
