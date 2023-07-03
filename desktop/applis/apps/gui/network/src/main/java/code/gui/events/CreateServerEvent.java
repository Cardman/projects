package code.gui.events;

import code.gui.AbstractDialogServer;

public class CreateServerEvent implements AbsActionListener {

    private final AbstractDialogServer dialog;

    public CreateServerEvent(AbstractDialogServer _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.createServerChoice();
    }
}
