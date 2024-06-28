package code.player.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRecorder implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private WindowRecorder windowRecorder;

    public CreateMainWindowRecorder(AbstractProgramInfos _p, String _l) {
        this.list = _p;
        this.lg = _l;
    }

    @Override
    public void run() {
        windowRecorder = new WindowRecorder(lg, list);
    }

    public WindowRecorder getWindowRecorder() {
        return windowRecorder;
    }
}
