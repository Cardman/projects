package code.gui.events;

import code.gui.OtherConfirmDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OtherClosingDialogEvent extends MouseAdapter {

    private OtherConfirmDialog dialog;

    public OtherClosingDialogEvent(OtherConfirmDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.closeWindow();
    }
}
