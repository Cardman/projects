package code.gui.files;

import code.gui.events.AbsActionListener;

public final class SaveSelectFileEvent implements AbsActionListener {
    private final AbsSaveFile saveFile;
    private final AbsContinueFile continueFile;
    private final FileSaveDialogContent saveDialogContent;

    public SaveSelectFileEvent(AbsSaveFile _s,AbsContinueFile _c, FileSaveDialogContent _f) {
        this.saveFile = _s;
        this.continueFile = _c;
        this.saveDialogContent = _f;
    }

    @Override
    public void action() {
        saveDialogContent.submitIfVisible();
        saveFile.save(saveDialogContent.getSelectedPath());
        continueFile.next(saveDialogContent);
    }
}
