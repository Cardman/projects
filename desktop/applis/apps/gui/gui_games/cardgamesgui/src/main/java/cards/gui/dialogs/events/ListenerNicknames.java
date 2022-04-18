package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogNicknames;
import code.gui.events.AbsActionListener;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerNicknames implements AbsActionListener {

    private DialogNicknames dialog;

    public ListenerNicknames(DialogNicknames _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateNicknames();
    }
}
