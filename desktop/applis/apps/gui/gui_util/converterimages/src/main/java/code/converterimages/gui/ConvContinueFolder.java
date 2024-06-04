package code.converterimages.gui;

import code.gui.AbsTextField;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class ConvContinueFolder implements AbsContinueFile {

    private final AbsTextField pathExport;

    public ConvContinueFolder(AbsTextField _p) {
        this.pathExport = _p;
    }

    @Override
    public void next(FileDialogContent _content) {
        pathExport.setText(_content.getSelectedAbsolutePath());
    }
}
