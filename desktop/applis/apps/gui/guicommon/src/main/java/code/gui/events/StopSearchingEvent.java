package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.FileOpenDialog;

public class StopSearchingEvent implements AbsActionListener{

    private FileOpenDialog dialog;

    private boolean newResults;

    public StopSearchingEvent(FileOpenDialog _dialog, boolean _newResults) {
        dialog = _dialog;
        newResults = _newResults;
    }

    @Override
    public void action() {
        dialog.setShowNewResults(newResults);
        dialog.setKeepSearching(false);
    }
}
