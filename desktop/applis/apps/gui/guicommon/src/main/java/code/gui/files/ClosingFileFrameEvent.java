package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.events.AbsWindowListenerClosing;

public final class ClosingFileFrameEvent implements AbsWindowListenerClosing {
    private final AbsCommonFrame commonFrame;
    private final AbsClosingFile cancelFile;

    public ClosingFileFrameEvent(AbsCommonFrame _o, AbsClosingFile _a) {
        this.commonFrame = _o;
        this.cancelFile = _a;
    }

    @Override
    public void windowClosing() {
        cancelFile.closeFrameFile(commonFrame, null);
    }
}
