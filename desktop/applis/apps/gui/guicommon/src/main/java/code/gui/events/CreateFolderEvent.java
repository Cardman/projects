package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.FileSaveDialog;

public class CreateFolderEvent extends MouseAdapter {

    private FileSaveDialog dialog;

    public CreateFolderEvent(FileSaveDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.createFolder();
    }
}
