package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogSoft;
import code.gui.events.AbsActionListener;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerParameters implements AbsActionListener {

    private final WindowCardsInt window;
    private final DialogSoft dialog;

    public ListenerParameters(WindowCardsInt _fenetre, DialogSoft _dialog) {
        window = _fenetre;
        dialog = _dialog;
    }

    @Override
    public void action() {
        dialog.validateParams(window);
    }
}
