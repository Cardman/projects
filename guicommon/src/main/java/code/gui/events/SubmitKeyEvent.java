package code.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.gui.SingleFileSelection;

public class SubmitKeyEvent implements ActionListener {

    private SingleFileSelection dialog;

    public SubmitKeyEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        dialog.submitIfVisible();
    }
}
