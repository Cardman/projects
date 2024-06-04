package code.gui.files;

import code.gui.events.AbsActionListener;

public final class SkipSelectFileEvent implements AbsActionListener {
    private final AbsContinueFile continueFile;
    private final FileSaveDialogContent saveDialogContent;

    public SkipSelectFileEvent(AbsContinueFile _c, FileSaveDialogContent _s) {
        this.continueFile = _c;
        this.saveDialogContent = _s;
    }

    @Override
    public void action() {
        saveDialogContent.getPostFileDialogEvent().act("");
        continueFile.next(saveDialogContent);
    }
}
