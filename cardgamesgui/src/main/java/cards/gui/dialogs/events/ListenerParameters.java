package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogSoft;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerParameters extends MouseAdapter {

    private DialogSoft dialog;

    public ListenerParameters(DialogSoft _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateParams();
    }
}
