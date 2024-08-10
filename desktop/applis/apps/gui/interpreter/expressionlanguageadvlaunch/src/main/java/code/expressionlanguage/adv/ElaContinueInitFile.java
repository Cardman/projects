package code.expressionlanguage.adv;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class ElaContinueInitFile implements AbsContinueFile {
    private final WindowCdmEditor window;

    public ElaContinueInitFile(WindowCdmEditor _w) {
        this.window = _w;
    }

    @Override
    public void next(FileDialogContent _content) {
        window.updateEnv(_content.getSelectedAbsolutePath());
        window.dateSave(MessagesIde.IDE_CHOOSE_PRO);
    }
}
