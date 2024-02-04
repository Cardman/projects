package code.gui.files;

import code.gui.events.AbsActionListener;

public final class SaveOpenSelectFileEvent implements AbsActionListener {
    private final FileOpenSaveFrame os;
    private final AbsSaveFile saveFile;
    private final AbsContinueFile continueFile;
    private final FileSaveDialogContent saveDialogContent;
    private final FileOpenDialogContent openDialogContent;

    public SaveOpenSelectFileEvent(FileOpenSaveFrame _openSave, AbsSaveFile _s, AbsContinueFile _c, FileSaveDialogContent _f, FileOpenDialogContent _o) {
        this.os = _openSave;
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
        os.getClosing().windowClosing();
        continueFile.next(openDialogContent);
    }
}
