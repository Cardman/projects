package code.expressionlanguage.gui.unit;

import code.threads.ThreadUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LoadFilesEvent implements ActionListener {
    private final SimpleFilesFrame filesFrame;

    public LoadFilesEvent(SimpleFilesFrame _filesFrame) {
        this.filesFrame = _filesFrame;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        ThreadUtil.start(new Thread(new LoadFiles(filesFrame)));
    }
}
