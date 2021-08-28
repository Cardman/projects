package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileOpenDialog;

public class SearchingEvent implements AbsActionListener {

    private FileOpenDialog dialog;

    public SearchingEvent(FileOpenDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.searchFile();
    }
}
