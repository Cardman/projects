package code.gui.files;


import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamFolderFile;

public final class FileSaveDialog extends FileDialog {
    public static final String FILE_SAVE_DIAL = "file_save";


    public FileSaveDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact, new FileSaveDialogContent(_frameFact));
    }

    public static void setFileSaveDialogByFrame(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _folder, FileSaveDialog _fileSave) {
        updateDialog(_w, _fileSave.getAbsDialog(), _fileSave.getProgramInfos());
        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialogByFrame(_language,_currentFolderRoot,_folder, new DefPostFileDialogEvent(_fileSave), new DefButtonsSavePanel());
    }

    public static void setFileSaveDialog(AbsDialog _w, String _language, boolean _currentFolderRoot, String _folder, FileSaveDialog _fileSave) {
        updateDialog(_w, _fileSave.getAbsDialog(), _fileSave.getProgramInfos());
        ((FileSaveDialogContent)_fileSave.getFileDialogContent()).setFileSaveDialog(_language,_currentFolderRoot,_folder, new DefPostFileDialogEvent(_fileSave), new DefButtonsSavePanel());
    }

    public static void updateDialog(AbsDialog _w, AbsDialog _target, AbstractProgramInfos _pr) {
        _target.setDialogIcon(_pr.getImageFactory(), _w);
        _target.setModal(true);
        _target.setLocationRelativeTo(_w);
    }

    public static void updateDialog(AbsCommonFrame _w, AbsDialog _target, AbstractProgramInfos _pr) {
        _target.setDialogIcon(_pr.getImageFactory(), _w);
        _target.setModal(true);
        _target.setLocationRelativeTo(_w);
    }

    static boolean koCreate(String _p, AbstractProgramInfos _pr) {
        return !_pr.getValidator().okPath(StreamFolderFile.getRelativeRootPath(_p,_pr.getFileCoreStream()), '/', '\\') || !StreamFolderFile.mkdirs(_p, _pr.getFileCoreStream());
    }

    public static String getStaticSelectedPath(FileSaveDialog _dialog) {
        return _dialog.getSelectedPath();
    }

    public AbsButton getSearch() {
        return ((FileSaveDialogContent)getFileDialogContent()).getSearch();
    }

    public AbsTextField getTypedString() {
        return ((FileSaveDialogContent) getFileDialogContent()).getTypedString();
    }
}
