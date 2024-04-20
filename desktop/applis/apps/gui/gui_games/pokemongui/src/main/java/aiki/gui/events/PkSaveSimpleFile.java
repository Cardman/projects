package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.files.AbsSaveFile;

public final class PkSaveSimpleFile implements AbsSaveFile {
    private final WindowAiki window;

    public PkSaveSimpleFile(WindowAiki _w) {
        this.window = _w;
    }

    @Override
    public String save(String _path) {
        window.save(_path);
        return _path;
    }
}
