package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class PkContinueFile implements AbsContinueFile {
    private final WindowAikiInt window;

    public PkContinueFile(WindowAikiInt _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.updateConf();
    }
}
