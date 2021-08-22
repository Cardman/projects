package cards.gui.dialogs.events;

import cards.gui.WindowCards;
import cards.gui.dialogs.DialogDisplaying;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class RemoveSuitEvent extends AbsMouseListenerRel {

    private DialogDisplaying dialog;
    private WindowCards window;

    public RemoveSuitEvent(DialogDisplaying _dialog, WindowCards _window) {
        dialog = _dialog;
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        dialog.removeSuit(window);
    }
}
