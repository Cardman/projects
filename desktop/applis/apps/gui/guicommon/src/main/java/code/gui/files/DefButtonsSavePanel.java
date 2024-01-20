package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsSavePanel extends AbsButtonsSavePanelImpl {

    @Override
    protected AbsActionListener buildEvent(FileSaveDialogContent _content) {
        return new SubmitMouseEvent(_content);
    }
}
