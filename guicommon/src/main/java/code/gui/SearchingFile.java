package code.gui;
import java.io.File;

/**Thread safe class*/
public final class SearchingFile extends Thread {

    private FileOpenDialog dialog;

    private File result;

    public SearchingFile(FileOpenDialog _dialog, File _result) {
        dialog = _dialog;
        result = _result;
    }

    @Override
    public void run() {
        dialog.getFileModel().setupFile(result);
    }
}
