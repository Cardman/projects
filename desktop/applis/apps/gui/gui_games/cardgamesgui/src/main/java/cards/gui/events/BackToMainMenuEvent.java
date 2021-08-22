package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class BackToMainMenuEvent extends AbsMouseListenerRel {

    private WindowCards window;

    public BackToMainMenuEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        window.menuPrincipal();
        window.pack();
    }
}
