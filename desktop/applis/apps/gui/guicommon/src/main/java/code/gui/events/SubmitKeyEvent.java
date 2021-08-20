package code.gui.events;

import code.gui.AbsActionListener;
import code.gui.SingleFileSelection;

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
