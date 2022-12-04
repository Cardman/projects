package cards.gui.menus;

import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class QuitMultiEvent implements AbsActionListener {

    private WindowNetWork window;

    public QuitMultiEvent(WindowNetWork _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.quitMulti();
    }
}
