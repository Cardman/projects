package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.WindowCards;

public class DisplayHelpGameEvent implements ActionListener {

    private WindowCards window;

    public DisplayHelpGameEvent(WindowCards _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.displayHelpGame();
    }
}
