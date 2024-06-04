package aiki.gui.events;

import aiki.gui.WindowAiki;
import aiki.gui.WindowAikiInt;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class PkContinueGameFile implements AbsContinueFile {
    private final WindowAikiInt window;

    public PkContinueGameFile(WindowAikiInt _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.updateConf();
        WindowAiki.loadGame(window, _content.getSelectedAbsolutePath());
    }
}
