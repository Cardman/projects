package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.WindowCards;

public class PauseEvent implements ActionListener {

    private WindowCards window;

    public PauseEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.pause();
    }
}
