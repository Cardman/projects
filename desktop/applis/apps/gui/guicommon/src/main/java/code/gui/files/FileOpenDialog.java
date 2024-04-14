package code.gui.files;




import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public final class FileOpenDialog extends FileDialog {
    public static final String FILE_OPEN_DIAL = "file_open";

//    private static final String ERROR_TYPING = "errorTyping";


    public FileOpenDialog(AbstractAtomicBoolean _keepSearching, AbstractAtomicBoolean _showNewResults, AbstractProgramInfos _frameFact){
        super(_frameFact, new FileOpenDialogContent(_keepSearching,_showNewResults,_frameFact));
    }
    public static void setFileOpenDialog(boolean _currentFolderRoot, String _folder, FileOpenDialog _fileOpen, AbsCommonFrame _fr) {
//        DIALOG.initFileOpenDialog(_w, _language, _currentFolderRoot, _extension, _folder, _excludedFolders);
        FileSaveDialog.updateDialog(_fr, _fileOpen.getAbsDialog(), _fileOpen.getProgramInfos());
        ((FileOpenDialogContent)_fileOpen.getFileDialogContent()).setFileOpenDialog(_currentFolderRoot,_folder, new DefPostFileDialogEvent(_fileOpen),new DefButtonsOpenPanel());
    }

//    private void initFileOpenDialog(GroupFrame _w,String _language,boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
//    }

    public AbsButton getSearchButton() {
        return content().getSearchButton();
    }

    public void submit() {
        content().submit();
    }

    private FileOpenDialogContent content() {
        return (FileOpenDialogContent) getFileDialogContent();
    }

//    @Override
//    public void closeWindow() {
////        if (thread != null) {
////            while (thread.isAlive()) {
////                continue;
////            }
////        }
//        setKeepSearching(false);
//        super.closeWindow();
//    }

    public static String getStaticSelectedPath(FileOpenDialog _dialog) {
        return _dialog.getSelectedPath();
    }

    public AbsTextField getTypedString() {
        return content().getTypedString();
    }

    public AbsButton getStop() {
        return content().getStop();
    }

    public void setKeepSearching(boolean _keepSearching) {
        content().getKeepSearching().set(_keepSearching);
    }

}
