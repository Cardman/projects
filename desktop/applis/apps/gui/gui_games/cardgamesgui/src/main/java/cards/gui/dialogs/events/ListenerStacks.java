package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogPresident;
import code.gui.events.AbsChangeListener;

public class ListenerStacks implements AbsChangeListener {

    private DialogPresident dialog;

    public ListenerStacks(DialogPresident _dialog) {
        dialog = _dialog;
    }

    @Override
    public void stateChanged() {
        dialog.validateStacks();
    }
}
