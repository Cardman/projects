package aiki.gui.components.editor;

import code.gui.files.*;
import code.gui.initialize.*;

public final class LoadDocImgEvent implements AbsContinueFile {
    private final AbstractProgramInfos api;
    private final ContentGeneComponentModelImg form;

    public LoadDocImgEvent(AbstractProgramInfos _core, ContentGeneComponentModelImg _f) {
        this.api = _core;
        this.form = _f;
    }

    @Override
    public void next(FileDialogContent _content) {
        form.tryLoad(api,_content.getSelectedAbsolutePath());
    }
}
