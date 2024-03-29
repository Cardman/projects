package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class MenuSoloGamesContinueFile implements AbsContinueFile {
    private final WindowCards window;

    public MenuSoloGamesContinueFile(WindowCards _w) {
        this.window = _w;
    }

    @Override
    public void next(FileDialogContent _content) {
        window.menuSoloGames();
    }
}
