package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileSaveDialog;

public class CreateFolderEvent extends AbsMouseListenerRel {

    private FileSaveDialog dialog;

    public CreateFolderEvent(FileSaveDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.createFolder();
    }
}
