package cards.gui.dialogs.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.dialogs.DialogNicknames;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerNicknames extends MouseAdapter {

    private DialogNicknames dialog;

    public ListenerNicknames(DialogNicknames _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        dialog.validateNicknames();
    }
}
