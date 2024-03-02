package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogVaryingStack;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerEqualityPlaying implements ListSelection {

    private final DialogVaryingStack dialog;

    public ListenerEqualityPlaying(DialogVaryingStack _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        dialog.displayMessagePlaying();
    }

}
