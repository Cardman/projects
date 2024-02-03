package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.AbsClosingFile;
import code.gui.files.FileDialogContent;
import code.threads.AbstractAtomicBoolean;

public final class CardsClosingLoadFile implements AbsClosingFile {
    private final AbstractAtomicBoolean modal;
    private final WindowCards window;

    public CardsClosingLoadFile(AbstractAtomicBoolean _m, WindowCards _w) {
        this.modal = _m;
        this.window = _w;
    }

    @Override
    public void closeFrameFile(AbsCommonFrame _frame, FileDialogContent _content) {
        _frame.setVisible(false);
        modal.set(false);
        if (_content != null) {
            String sel_ = _content.getSelectedAbsolutePath();
            if (!sel_.isEmpty()) {
                window.tryToLoadDeal(sel_);
            }
        }
    }
}
