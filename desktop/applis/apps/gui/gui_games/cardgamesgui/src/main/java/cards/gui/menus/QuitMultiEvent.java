package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class QuitMultiEvent implements AbsActionListener {

    private WindowCards window;

    public QuitMultiEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.quitMulti();
    }
}
