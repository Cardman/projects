package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileOpenDialog;

public class StopSearchingEvent extends AbsMouseListenerRel {

    private FileOpenDialog dialog;

    private boolean newResults;

    public StopSearchingEvent(FileOpenDialog _dialog, boolean _newResults) {
        dialog = _dialog;
        newResults = _newResults;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.setShowNewResults(newResults);
        dialog.setKeepSearching(false);
    }
}
