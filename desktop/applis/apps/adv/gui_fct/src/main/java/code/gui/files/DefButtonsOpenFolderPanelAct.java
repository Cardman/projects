package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsOpenFolderPanelAct extends AbsButtonsOpenFolderPanelImpl {
    private final AbsContinueFile continueFile;

    public DefButtonsOpenFolderPanelAct(AbsContinueFile _c) {
        this.continueFile = _c;
    }
    @Override
    protected AbsActionListener buildEvent(FolderOpenDialogContent _content) {
        return new OpenSelectFileEvent(continueFile,_content);
    }
}
