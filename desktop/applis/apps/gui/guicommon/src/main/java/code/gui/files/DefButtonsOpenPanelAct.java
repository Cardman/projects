package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsOpenPanelAct extends AbsButtonsOpenPanelImpl {
    private final AbsContinueLoadFile continueFile;

    public DefButtonsOpenPanelAct(AbsContinueLoadFile _c) {
        this.continueFile = _c;
    }
    @Override
    protected AbsActionListener buildEvent(FileOpenDialogContent _content) {
        return new OpenSelectFileEvent(continueFile,_content);
    }
}
