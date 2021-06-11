package code.gui;
import code.stream.AbstractFile;


/**Thread safe class*/
public final class SearchingFile implements Runnable {

    private FileOpenDialog dialog;

    private AbstractFile result;

    public SearchingFile(FileOpenDialog _dialog, AbstractFile _result) {
        dialog = _dialog;
        result = _result;
    }

    @Override
    public void run() {
        dialog.getFileModel().setupFile(result);
    }
}
