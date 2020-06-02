package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.Dialog;

public class ClosingDialogEvent extends MouseAdapter {

    private Dialog dialog;

    public ClosingDialogEvent(Dialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.closeWindow();
    }
}
