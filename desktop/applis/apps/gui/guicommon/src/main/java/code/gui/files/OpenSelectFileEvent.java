package code.gui.files;

import code.gui.events.AbsActionListener;

public final class OpenSelectFileEvent implements AbsActionListener {
    private final AbsContinueFile continueFile;
    private final FileOpenDialogContent openDialogContent;

    public OpenSelectFileEvent(AbsContinueFile _c, FileOpenDialogContent _f) {
        this.continueFile = _c;
        this.openDialogContent = _f;
    }

    @Override
    public void action() {
        openDialogContent.submitIfVisible();
        continueFile.next(openDialogContent);
    }
}
