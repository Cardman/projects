package code.gui.files;

import code.gui.events.AbsActionListener;

public class StopSearchingEvent implements AbsActionListener{

    private final FileOpenDialog dialog;

    private final boolean newResults;

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
