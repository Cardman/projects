package code.gui.files;
import code.stream.AbstractFile;


/**Thread safe class*/
public final class SearchingFile implements Runnable {

    private FileOpenDialogContent dialog;

    private AbstractFile result;

    public SearchingFile(FileOpenDialogContent _dialog, AbstractFile _result) {
        dialog = _dialog;
        result = _result;
    }

    @Override
    public void run() {
        dialog.getFileModel().setupFile(result);
    }
}
