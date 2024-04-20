package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.files.AbsSaveFile;

public final class PkSaveFile implements AbsSaveFile {
    private final WindowAiki window;

    public PkSaveFile(WindowAiki _w) {
        this.window = _w;
    }

    @Override
    public String save(String _path) {
        window.exportSaveFile(_path);
        return _path;
    }
}
