package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileOpenDialog;

public class SearchingEvent extends AbsMouseListenerRel {

    private FileOpenDialog dialog;

    public SearchingEvent(FileOpenDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.searchFile();
    }
}
