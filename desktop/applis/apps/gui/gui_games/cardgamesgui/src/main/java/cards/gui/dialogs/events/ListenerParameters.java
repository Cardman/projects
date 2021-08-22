package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogSoft;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

/**Classe d'ecouteur speciale a ce type de boite de dialogue*/
public class ListenerParameters extends AbsMouseListenerRel {

    private DialogSoft dialog;

    public ListenerParameters(DialogSoft _dialog) {
        dialog = _dialog;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.validateParams();
    }
}
