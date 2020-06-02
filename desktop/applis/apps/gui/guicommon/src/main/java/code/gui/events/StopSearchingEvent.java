package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.FileOpenDialog;

public class StopSearchingEvent extends MouseAdapter {

    private FileOpenDialog dialog;

    private boolean newResults;

    public StopSearchingEvent(FileOpenDialog _dialog, boolean _newResults) {
        dialog = _dialog;
        newResults = _newResults;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.setShowNewResults(newResults);
        dialog.setKeepSearching(false);
    }
}
