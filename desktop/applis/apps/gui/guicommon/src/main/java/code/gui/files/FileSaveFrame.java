package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;

public final class FileSaveFrame extends FileFrame {
    public FileSaveFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a) {
        super(_frameFact, new FileSaveDialogContent(_frameFact),_a);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialogByFrame(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave), _build);
    }
//
//    public static void setFileSaveDialog(String _language, boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
//        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialog(_language,_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave), _build);
//    }
}
