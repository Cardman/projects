package cards.gui;

import code.gui.AbsCommonFrame;
import code.gui.files.FileDialogContent;
import code.threads.AbstractAtomicBoolean;

public final class CardsClosingFile extends AbsCardsClosingFile {

    public CardsClosingFile(AbstractAtomicBoolean _m) {
        super(_m);
    }

    @Override
    public void closeFrameFile(AbsCommonFrame _frame, FileDialogContent _content) {
        closeFrameFile(_frame);
    }
}
