package code.gui.files;

import code.gui.events.AbsActionListener;

public class SubmitMouseEvent implements AbsActionListener {

    private final FileDialogContent dialog;

    public SubmitMouseEvent(FileDialogContent _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.submitIfVisible();
    }
}
