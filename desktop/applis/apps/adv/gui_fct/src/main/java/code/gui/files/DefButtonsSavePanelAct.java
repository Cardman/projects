package code.gui.files;

import code.gui.events.AbsActionListener;

public final class DefButtonsSavePanelAct extends AbsButtonsSavePanelImpl {
    private final AbsSaveFile saveFile;
    private final AbsContinueFile continueFile;

    public DefButtonsSavePanelAct(AbsSaveFile _s,AbsContinueFile _c) {
        this.saveFile = _s;
        this.continueFile = _c;
    }
    @Override
    protected AbsActionListener buildEvent(FileSaveDialogContent _content) {
        return new SaveSelectFileEvent(saveFile,continueFile,_content);
    }
}
