package code.player.gui;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRecorder implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final LanguagesButtonsPair pair;
    private WindowRecorder windowRecorder;

    public CreateMainWindowRecorder(AbstractProgramInfos _p, String _l, LanguagesButtonsPair _pa) {
        this.list = _p;
        this.lg = _l;
        pair = _pa;
    }

    @Override
    public void run() {
        windowRecorder = new WindowRecorder(lg, list, pair);
    }

    public WindowRecorder getWindowRecorder() {
        return windowRecorder;
    }
}
