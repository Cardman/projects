package code.gui;
import java.io.File;

import code.util.CustList;
import code.util.StringList;

/**Thread safe class*/
public final class AfterSearchingFile implements Runnable {

    private FileOpenDialog dialog;

//    private Cursor cursor;

//    private boolean finished;

//    private List<File> results;

    private CustList<File> backup;

    public AfterSearchingFile(FileOpenDialog _dialog, CustList<File> _backup) {
        dialog = _dialog;
//        cursor = _cursor;
//        finished = _finished;
//        results = _results;
        backup = _backup;
    }

    @Override
    public void run() {
        /*if (finished) {
            dialog.getFileModel().setupFiles(results,dialog.getCurrentFolder(), dialog.getExtension());
        } else*/
        if (!dialog.isShowNewResults()) {
            dialog.refreshList(backup);
        }
        /*else {
            dialog.getFileModel().setupFiles(results,dialog.getCurrentFolder(), dialog.getExtension());
        }*/
//        dialog.setCursor(cursor);
        dialog.setKeepSearching(false);
    }
}
