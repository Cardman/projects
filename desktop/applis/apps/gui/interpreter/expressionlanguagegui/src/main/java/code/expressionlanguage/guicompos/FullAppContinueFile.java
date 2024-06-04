package code.expressionlanguage.guicompos;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class FullAppContinueFile implements AbsContinueFile {
    private final WindowFull windowFull;

    public FullAppContinueFile(WindowFull _w) {
        this.windowFull = _w;
    }

    @Override
    public void next(FileDialogContent _content) {
        windowFull.launchFileConf(_content.getSelectedAbsolutePath(),true);
    }
}
