package cards.gui.menus;

import cards.gui.WindowCards;
import code.gui.AbsActionListener;

public class ConsultEvent implements AbsActionListener {

    private WindowCards window;

    public ConsultEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.consult();
    }
}
