package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogSoft;
import code.gui.events.AbsActionListener;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerParameters implements AbsActionListener {

    private DialogSoft dialog;

    public ListenerParameters(DialogSoft _dialog) {
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateParams();
    }
}
