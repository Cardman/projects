package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class PkContinueRomFile implements AbsContinueFile {
    private final WindowAiki window;

    public PkContinueRomFile(WindowAiki _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.updateConf();
        window.loadRom(_content.getSelectedAbsolutePath());
    }
}
