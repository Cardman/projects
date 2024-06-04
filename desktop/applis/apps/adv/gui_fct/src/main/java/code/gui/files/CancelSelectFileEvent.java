package code.gui.files;

import code.gui.events.AbsActionListener;

public class CancelSelectFileEvent implements AbsActionListener {

    private final FileDialogContent dialog;

    public CancelSelectFileEvent(FileDialogContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.getPostFileDialogEvent().act("");
    }
}
