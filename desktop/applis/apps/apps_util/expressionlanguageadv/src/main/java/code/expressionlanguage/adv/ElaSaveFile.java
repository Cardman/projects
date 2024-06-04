package code.expressionlanguage.adv;

import code.gui.files.AbsSaveFile;

public final class ElaSaveFile implements AbsSaveFile {
    private final WindowCdmEditor window;

    public ElaSaveFile(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public String save(String _path) {
        window.saveConf(_path);
        return _path;
    }

}
