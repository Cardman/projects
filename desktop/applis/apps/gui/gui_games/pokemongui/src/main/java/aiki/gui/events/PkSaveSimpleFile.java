package aiki.gui.events;

import aiki.gui.WindowAikiInt;
import code.gui.files.AbsSaveFile;

public final class PkSaveSimpleFile implements AbsSaveFile {
    private final WindowAikiInt window;

    public PkSaveSimpleFile(WindowAikiInt _w) {
        this.window = _w;
    }

    @Override
    public String save(String _path) {
        window.save(_path);
        return _path;
    }
}
