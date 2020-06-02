package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.SingleFileSelection;

public class SubmitMouseEvent extends MouseAdapter {

    private SingleFileSelection dialog;

    public SubmitMouseEvent(SingleFileSelection _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.submitIfVisible();
    }
}
