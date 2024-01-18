package code.gui.files;

import code.gui.events.AbsActionListener;

public class CreateFolderEvent implements AbsActionListener {

    private final FileSaveDialogContent dialog;

    public CreateFolderEvent(FileSaveDialogContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.createFolder();
    }
}
