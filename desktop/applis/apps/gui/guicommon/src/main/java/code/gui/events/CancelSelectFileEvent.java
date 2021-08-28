package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileDialog;

public class CancelSelectFileEvent implements AbsActionListener {

    private FileDialog dialog;

    public CancelSelectFileEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        if (!dialog.isVisible()) {
            return;
        }
        dialog.setSelectedPath(null);
        dialog.closeWindow();
    }
}
