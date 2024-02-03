package code.gui.files;

import code.gui.AbsCommonFrame;

public final class ClosingFileSample implements AbsClosingFile {
    @Override
    public void closeFrameFile(AbsCommonFrame _frame) {
        _frame.setVisible(false);
    }
}
