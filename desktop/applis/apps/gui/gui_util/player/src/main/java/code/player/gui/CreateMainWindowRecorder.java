package code.player.gui;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRecorder implements Runnable {
    private final AbstractProgramInfos list;
    private final LanguagesButtonsPair pair;
    private WindowRecorder windowRecorder;

    public CreateMainWindowRecorder(AbstractProgramInfos _p, LanguagesButtonsPair _pa) {
        this.list = _p;
        pair = _pa;
    }

    @Override
    public void run() {
        windowRecorder = new WindowRecorder(list, pair);
    }

    public WindowRecorder getWindowRecorder() {
        return windowRecorder;
    }
}
