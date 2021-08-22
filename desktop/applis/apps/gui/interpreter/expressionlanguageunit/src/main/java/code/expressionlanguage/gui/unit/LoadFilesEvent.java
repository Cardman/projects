package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;

public final class LoadFilesEvent implements AbsActionListener {
    private final SimpleFilesFrame filesFrame;

    public LoadFilesEvent(SimpleFilesFrame _filesFrame) {
        this.filesFrame = _filesFrame;
    }

    @Override
    public void action() {
        filesFrame.getThreadFactory().newStartedThread(new LoadFiles(filesFrame));
    }
}
