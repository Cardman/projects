package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.FileDialogContent;
import code.threads.AbstractAtomicBoolean;

public final class CardsClosingLoadFile extends AbsCardsClosingFile {
    private final WindowCards window;

    public CardsClosingLoadFile(AbstractAtomicBoolean _m, WindowCards _w) {
        super(_m);
        this.window = _w;
    }

    @Override
    public void closeFrameFile(AbsCommonFrame _frame, FileDialogContent _content) {
        closeFrameFile(_frame);
        if (_content != null) {
            String sel_ = _content.getSelectedAbsolutePath();
            if (!sel_.isEmpty()) {
                window.tryToLoadDeal(sel_);
            }
        }
    }
}
