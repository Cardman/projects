package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.files.*;

public final class MenuLoadGameContinueFile implements AbsContinueLoadFile {
    private final WindowCards window;

    public MenuLoadGameContinueFile(WindowCards _w) {
        this.window = _w;
    }

    @Override
    public void next(FileOpenDialogContent _content) {
        window.tryToLoadDeal(_content.getSelectedAbsolutePath());
    }
}
