package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileDialog;

public class CancelSelectFileEvent extends AbsMouseListenerRel {

    private FileDialog dialog;

    public CancelSelectFileEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!dialog.isVisible()) {
            return;
        }
        dialog.setSelectedPath(null);
        dialog.closeWindow();
    }
}
