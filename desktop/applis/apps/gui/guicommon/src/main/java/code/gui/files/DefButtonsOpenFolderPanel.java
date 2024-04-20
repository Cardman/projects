package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsOpenFolderPanel extends AbsButtonsOpenFolderPanelImpl {

    @Override
    protected AbsActionListener buildEvent(FolderOpenDialogContent _content) {
        return new SubmitMouseEvent(_content);
    }
}
