package aiki.gui.components.editor;

import code.gui.files.*;

public final class PkSaveEdited implements AbsSaveFile {
    private final WindowPkEditor window;

    public PkSaveEdited(WindowPkEditor _w) {
        this.window = _w;
    }

    @Override
    public String save(String _path) {
        window.saveData(_path);
        return _path;
    }
}
