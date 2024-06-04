package code.gui.files;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsWindowListenerClosing;

public class CloseSelectFileEvent implements AbsActionListener {

    private final AbsWindowListenerClosing dialog;

    public CloseSelectFileEvent(AbsWindowListenerClosing _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.windowClosing();
    }
}
