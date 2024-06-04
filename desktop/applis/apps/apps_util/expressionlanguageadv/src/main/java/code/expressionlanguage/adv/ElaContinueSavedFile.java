package code.expressionlanguage.adv;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class ElaContinueSavedFile implements AbsContinueFile {
    private final WindowCdmEditor window;

    public ElaContinueSavedFile(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public void next(FileDialogContent _content) {
        window.dateSave();
    }
}
