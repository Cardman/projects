package aiki.gui.components.editor;

import code.gui.files.*;

public final class LoadDocImgEvent implements AbsContinueFile {
    private final GeneComponentModelImg form;

    public LoadDocImgEvent(GeneComponentModelImg _f) {
        this.form = _f;
    }

    @Override
    public void next(FileDialogContent _content) {
        form.tryLoad(_content.getSelectedAbsolutePath());
    }
}
