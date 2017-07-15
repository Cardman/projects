package code.gui.events;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import code.gui.Dialog;

public class CrossClosingDialogEvent extends WindowAdapter {

    private Dialog dialog;

    public CrossClosingDialogEvent(Dialog _dialog) {
        dialog = _dialog;
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        dialog.closeWindow();
    }
}
