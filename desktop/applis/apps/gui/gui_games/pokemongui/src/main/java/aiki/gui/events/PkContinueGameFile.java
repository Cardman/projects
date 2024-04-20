package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class PkContinueGameFile implements AbsContinueFile {
    private final WindowAiki window;

    public PkContinueGameFile(WindowAiki _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.updateConf();
        window.loadGame(_content.getSelectedAbsolutePath());
    }
}
