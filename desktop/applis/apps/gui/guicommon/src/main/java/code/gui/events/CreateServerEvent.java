package code.gui.events;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.AbstractDialogServer;

public class CreateServerEvent implements AbsActionListener {

    private AbstractDialogServer dialog;

    public CreateServerEvent(AbstractDialogServer _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.createServerChoice();
    }
}
