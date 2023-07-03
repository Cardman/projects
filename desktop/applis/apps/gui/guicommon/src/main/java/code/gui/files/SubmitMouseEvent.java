package code.gui.files;

import code.gui.events.AbsActionListener;

public class SubmitMouseEvent implements AbsActionListener {

    private final SingleFileSelection dialog;

    public SubmitMouseEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.submitIfVisible();
    }
}
