package aiki.gui.components.editor;

import code.gui.files.*;

public final class PkContinueLoadedEdited implements AbsContinueFile {
    private final WindowPkEditor window;

    public PkContinueLoadedEdited(WindowPkEditor _w) {
        this.window = _w;
    }
    @Override
    public void next(FileDialogContent _content) {
        window.loadData(_content.getSelectedAbsolutePath());
    }
}
