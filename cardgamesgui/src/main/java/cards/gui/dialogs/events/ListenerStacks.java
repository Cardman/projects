package cards.gui.dialogs.events;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cards.gui.dialogs.DialogPresident;

public class ListenerStacks implements ChangeListener {

    private DialogPresident dialog;

    public ListenerStacks(DialogPresident _dialog) {
        dialog = _dialog;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        dialog.validateStacks();
    }
}
