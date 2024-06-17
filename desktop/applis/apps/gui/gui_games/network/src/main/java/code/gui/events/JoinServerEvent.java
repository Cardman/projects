package code.gui.events;

import code.gui.AbstractDialogServer;

public class JoinServerEvent implements AbsActionListener {

    private final AbstractDialogServer dialog;

    public JoinServerEvent(AbstractDialogServer _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.joinServerChoice();
    }
}
