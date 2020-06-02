package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.AbstractDialogServer;

public class CreateServerEvent extends MouseAdapter {

    private AbstractDialogServer dialog;

    public CreateServerEvent(AbstractDialogServer _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.createServerChoice();
    }
}
