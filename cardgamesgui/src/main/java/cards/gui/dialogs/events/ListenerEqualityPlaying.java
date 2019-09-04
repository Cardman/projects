package cards.gui.dialogs.events;
import cards.gui.dialogs.DialogPresident;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ListenerEqualityPlaying implements ListSelection {

    private DialogPresident dialog;

    public ListenerEqualityPlaying(DialogPresident _dialog) {
        dialog = _dialog;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        dialog.displayMessagePlaying();
    }

}
