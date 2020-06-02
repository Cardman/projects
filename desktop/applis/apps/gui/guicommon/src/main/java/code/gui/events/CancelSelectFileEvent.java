package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.FileDialog;

public class CancelSelectFileEvent extends MouseAdapter {

    private FileDialog dialog;

    public CancelSelectFileEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!dialog.isVisible()) {
            return;
        }
        dialog.setSelectedPath(null);
        dialog.closeWindow();
    }
}
