package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.DialogNicknames;
import code.gui.events.AbsActionListener;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerNicknames implements AbsActionListener {

    private final WindowCards window;
    private final DialogNicknames dialog;

    public ListenerNicknames(WindowCards _fenetre, DialogNicknames _dialog) {
        window = _fenetre;
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateNicknames(window);
    }
}
