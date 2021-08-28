package code.gui.events;

import code.gui.*;

public class ClosingDialogEvent implements AbsActionListener {

    private AbsCloseableDialog dialog;

    public ClosingDialogEvent(AbsCloseableDialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.closeWindow();
    }
}
