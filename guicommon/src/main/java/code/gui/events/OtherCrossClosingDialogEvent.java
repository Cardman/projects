package code.gui.events;

import code.gui.OtherConfirmDialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OtherCrossClosingDialogEvent extends WindowAdapter {

    private OtherConfirmDialog dialog;

    public OtherCrossClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        dialog.closeWindow();
    }
}
