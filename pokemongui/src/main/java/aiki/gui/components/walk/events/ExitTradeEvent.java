package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.MainWindow;
import aiki.network.stream.Quit;
import code.util.consts.Constants;

public class ExitTradeEvent extends MouseAdapter {

    private MainWindow window;

    public ExitTradeEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        Quit quit_ = new Quit();
        quit_.setClosing(false);
        quit_.setPlace(window.getIndexInGame());
        quit_.setLocale(Constants.getLanguage());
        window.sendObject(quit_);
    }
}
