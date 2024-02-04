package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;

public final class FileSaveFrame extends FileFrame implements FileFrameInt {
    private final FileSaveDialogContent fileDialogContent;
    public FileSaveFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a) {
        super(_frameFact, _a);
        fileDialogContent = new FileSaveDialogContent(_frameFact);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
        FileSaveDialogContent c_ = _fileSave.getFileDialogContent();
        c_.setFileSaveDialogByFrame(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave, c_, _fileSave.getFrame()), _build);
    }

    @Override
    public void closeFrameFile() {
        getCancelFile().closeFrameFile(getFrame(), getFileDialogContent());
    }
    public FileSaveDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
//
//    public static void setFileSaveDialog(String _language, boolean _currentFolderRoot, String _folder, FileSaveFrame _fileSave, AbsButtonsSavePanel _build) {
//        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialog(_language,_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave), _build);
//    }
}
