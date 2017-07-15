package cards.gui.dialogs.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.dialogs.DialogPresident;

public class ListenerEqualityPlaying implements ActionListener {

    private DialogPresident dialog;

    public ListenerEqualityPlaying(DialogPresident _dialog) {
        dialog = _dialog;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        dialog.displayMessagePlaying();
    }

}
