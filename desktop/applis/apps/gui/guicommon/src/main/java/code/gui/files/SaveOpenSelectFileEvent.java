package code.gui.files;

import code.gui.events.AbsActionListener;

public final class SaveOpenSelectFileEvent implements AbsActionListener {
    private final AbsSaveFile saveFile;
    private final AbsContinueLoadFile continueFile;
    private final FileSaveDialogContent saveDialogContent;
    private final FileOpenDialogContent openDialogContent;

    public SaveOpenSelectFileEvent(AbsSaveFile _s, AbsContinueLoadFile _c, FileSaveDialogContent _f, FileOpenDialogContent _o) {
        this.saveFile = _s;
        this.continueFile = _c;
        this.saveDialogContent = _f;
        this.openDialogContent = _o;
    }

    @Override
    public void action() {
        saveDialogContent.submitIfVisible();
        openDialogContent.submitIfVisible();
        String path_ = saveDialogContent.getSelectedPath();
        if (!path_.isEmpty()) {
            saveFile.save(path_);
        }
        continueFile.next(openDialogContent);
    }
}
