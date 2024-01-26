/**
    */
package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

/**
 */
public final class FolderOpenDialog extends FileDialog {
    public static final String FOLDER_OPEN_DIAL = "folder_open";

    public FolderOpenDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact, new FolderOpenDialogContent(_frameFact));
    }
    public static void setFolderOpenDialog(boolean _currentFolderRoot, FolderOpenDialog _folder, AbsCommonFrame _fr) {
        FileSaveDialog.updateDialog(_fr, _folder.getAbsDialog(), _folder.getProgramInfos());
        ((FolderOpenDialogContent)_folder.getFileDialogContent()).setFolderOpenDialog(_currentFolderRoot, new DefPostFileDialogEvent(_folder));
    }

    public static String getStaticSelectedPath(FolderOpenDialog _dialog) {
        return _dialog.getSelectedPath();
    }

}
