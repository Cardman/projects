package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;

public final class FileOpenFrame extends FileFrame {
    public FileOpenFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a) {
        super(_frameFact, new FileOpenDialogContent(_frameFact.getThreadFactory().newAtomicBoolean(), _frameFact.getThreadFactory().newAtomicBoolean(),_frameFact),_a);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileOpenFrame _fileSave) {
        ((FileOpenDialogContent)_fileSave.getFileDialogContent()).setFileOpenDialog(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave));
    }
}
