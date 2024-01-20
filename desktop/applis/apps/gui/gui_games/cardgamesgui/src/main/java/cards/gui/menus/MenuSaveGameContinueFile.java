package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileSaveDialogContent;

public final class MenuSaveGameContinueFile implements AbsContinueFile {
    private final WindowCards window;

    public MenuSaveGameContinueFile(WindowCards _w) {
        this.window = _w;
    }

    @Override
    public void next(FileSaveDialogContent _content) {
        window.dateSave();
    }
}
