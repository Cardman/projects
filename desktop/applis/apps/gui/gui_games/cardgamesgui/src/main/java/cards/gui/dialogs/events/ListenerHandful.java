package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogTarot;

public class ListenerHandful extends MouseAdapter {

    private DialogTarot dialog;

    public ListenerHandful(DialogTarot _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateHandfulTrumps();
    }
}
