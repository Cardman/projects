package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.events.AbsWindowListenerClosing;
import code.threads.AbstractAtomicBoolean;

public final class ClosingFileFrameEvent implements AbsWindowListenerClosing {
    private final AbsCommonFrame commonFrame;
    private final AbstractAtomicBoolean cancelFile;

    public ClosingFileFrameEvent(AbsCommonFrame _o, AbstractAtomicBoolean _a) {
        this.commonFrame = _o;
        this.cancelFile = _a;
    }

    @Override
    public void windowClosing() {
        commonFrame.setVisible(false);
        cancelFile.set(false);
    }
}
