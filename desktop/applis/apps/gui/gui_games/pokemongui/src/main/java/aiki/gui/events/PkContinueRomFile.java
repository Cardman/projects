package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import aiki.gui.threads.LoadingThread;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;
import code.threads.AbstractAtomicIntegerCoreAdd;

public final class PkContinueRomFile implements AbsContinueFile {
    private final WindowAikiInt window;

    public PkContinueRomFile(WindowAikiInt _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.updateConf();
        AbstractAtomicIntegerCoreAdd p_ = window.getFrames().getThreadFactory().newAtomicInteger();
        window.getLoadFlag().set(true);
        LoadingThread load_ = new LoadingThread(window, _content.getSelectedAbsolutePath(),p_);
        window.getFrames().getThreadFactory().newStartedThread(load_);
//        window.loadRom(_content.getSelectedAbsolutePath());
    }
}
