package code.gui.files;

import code.gui.FileSaveDialog;
import code.gui.events.AbsActionListener;

public class CreateFolderEvent implements AbsActionListener {

    private final FileSaveDialog dialog;

    public CreateFolderEvent(FileSaveDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.createFolder();
    }
}
