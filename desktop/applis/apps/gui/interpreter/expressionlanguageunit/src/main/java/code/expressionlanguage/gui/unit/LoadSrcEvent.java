package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;

public final class LoadSrcEvent implements AbsActionListener {
    private final SimpleFilesFrame filesFrame;

    public LoadSrcEvent(SimpleFilesFrame _filesFrame) {
        this.filesFrame = _filesFrame;
    }

    @Override
    public void action() {
        filesFrame.getInfos().getThreadFactory().newStartedThread(new LoadSrc(filesFrame));
    }
}
