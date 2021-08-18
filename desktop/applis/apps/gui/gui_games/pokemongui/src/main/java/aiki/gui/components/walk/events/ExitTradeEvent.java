package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.WindowAiki;
import aiki.network.stream.QuitAiki;

public class ExitTradeEvent extends MouseAdapter {

    private WindowAiki window;

    public ExitTradeEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        QuitAiki quit_ = new QuitAiki();
        quit_.setClosing(false);
        quit_.setPlace(window.getIndexInGame());
        quit_.setLocale(window.getLanguageKey());
        window.sendObject(quit_);
    }
}
