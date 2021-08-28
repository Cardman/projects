package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileSaveDialog;

public class CreateFolderEvent implements AbsActionListener {

    private FileSaveDialog dialog;

    public CreateFolderEvent(FileSaveDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.createFolder();
    }
}
