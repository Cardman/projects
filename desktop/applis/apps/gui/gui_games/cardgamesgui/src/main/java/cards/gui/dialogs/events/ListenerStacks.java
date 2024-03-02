package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogVaryingStack;
import code.gui.events.AbsChangeListener;

public class ListenerStacks implements AbsChangeListener {

    private final DialogVaryingStack dialog;

    public ListenerStacks(DialogVaryingStack _dialog) {
        dialog = _dialog;
    }

    @Override
    public void stateChanged() {
        dialog.validateStacks();
    }
}
