package code.player.gui;

import code.gui.AbsButton;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRecorder implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final AbsButton mainButton;
    private WindowRecorder windowRecorder;

    public CreateMainWindowRecorder(AbstractProgramInfos _p, String _l, AbsButton _ma) {
        this.list = _p;
        this.lg = _l;
        mainButton = _ma;
    }

    @Override
    public void run() {
        windowRecorder = new WindowRecorder(lg, list, mainButton);
    }

    public WindowRecorder getWindowRecorder() {
        return windowRecorder;
    }
}
