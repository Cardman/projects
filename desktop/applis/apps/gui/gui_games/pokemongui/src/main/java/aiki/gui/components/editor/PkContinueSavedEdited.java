package aiki.gui.components.editor;

import code.gui.files.*;

public final class PkContinueSavedEdited implements AbsContinueFile {
    private final WindowPkEditor window;

    public PkContinueSavedEdited(WindowPkEditor _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.lastDate();
    }
}
