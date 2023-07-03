package code.gui.files;

import code.gui.events.AbsActionListener;

public class SubmitKeyEvent implements AbsActionListener {

    private SingleFileSelection dialog;

    public SubmitKeyEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.submitIfVisible();
    }
}
