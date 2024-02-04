package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;

public final class FileOpenFrame extends FileFrame implements FileFrameInt {
    private final FileOpenDialogContent fileDialogContent;
    public FileOpenFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a) {
        super(_frameFact, _a);
        fileDialogContent = new FileOpenDialogContent(_frameFact.getThreadFactory().newAtomicBoolean(), _frameFact.getThreadFactory().newAtomicBoolean(),_frameFact);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileOpenFrame _fileSave) {
        FileOpenDialogContent c_ = _fileSave.getFileDialogContent();
        c_.setFileOpenDialog(_currentFolderRoot,_folder, new DefPostFileFrameEvent(_fileSave, c_, _fileSave.getFrame()));
    }

    @Override
    public void closeFrameFile() {
        getCancelFile().closeFrameFile(getFrame(), getFileDialogContent());
    }
    public FileOpenDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
