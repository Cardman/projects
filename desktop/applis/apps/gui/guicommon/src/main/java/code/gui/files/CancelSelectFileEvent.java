package code.gui.files;

import code.gui.events.AbsActionListener;

public class CancelSelectFileEvent implements AbsActionListener {

    private final FileDialog dialog;

    public CancelSelectFileEvent(FileDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.setSelectedPath("");
        dialog.closeWindow();
    }
}
