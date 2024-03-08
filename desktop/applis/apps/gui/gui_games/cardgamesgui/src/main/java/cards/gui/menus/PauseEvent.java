package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;

public class PauseEvent implements AbsActionListener {

    private final WindowCards window;
    private final EnabledMenu pause;

    public PauseEvent(WindowCards _window, EnabledMenu _p) {
        window = _window;
        this.pause = _p;
    }

    @Override
    public void action() {
        pause.setEnabled(false);
        window.pause();
    }
}
