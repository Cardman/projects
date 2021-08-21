package code.gui.events;

import code.gui.Dialog;

public class CrossClosingDialogEvent extends AbsWindowListenerClosing {

    private Dialog dialog;

    public CrossClosingDialogEvent(Dialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing() {
        dialog.closeWindow();
    }
}
