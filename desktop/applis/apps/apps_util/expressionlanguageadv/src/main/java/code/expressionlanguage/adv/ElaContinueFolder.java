package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.MemoryFileSystem;
import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class ElaContinueFolder implements AbsContinueFile {
    private final SetupableFolder setupableFolder;

    public ElaContinueFolder(SetupableFolder _s) {
        this.setupableFolder = _s;
    }

    @Override
    public void next(FileDialogContent _content) {
        setupableFolder.folder(MemoryFileSystem.skipLastSep(_content.getSelectedAbsolutePath()));
    }
}
