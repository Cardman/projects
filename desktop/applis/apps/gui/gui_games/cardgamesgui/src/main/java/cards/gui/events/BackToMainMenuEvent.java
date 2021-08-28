package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class BackToMainMenuEvent implements AbsActionListener {

    private WindowCards window;

    public BackToMainMenuEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.menuPrincipal();
        window.pack();
    }
}
