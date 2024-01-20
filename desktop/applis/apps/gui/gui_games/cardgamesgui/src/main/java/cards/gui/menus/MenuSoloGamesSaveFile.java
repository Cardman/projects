package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.files.AbsSaveFile;

public final class MenuSoloGamesSaveFile implements AbsSaveFile {
    private final WindowCards window;

    public MenuSoloGamesSaveFile(WindowCards _w) {
        this.window = _w;
    }
    @Override
    public String save(String _path) {
        window.getCore().getContainerGame().saveCurrentGame(_path,window.getStreams());
        return _path;
    }
}
