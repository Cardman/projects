package code.gui.files;

import code.gui.events.AbsActionListener;

public class CancelSelectFileEvent implements AbsActionListener {

    private final FileDialog dialog;

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
