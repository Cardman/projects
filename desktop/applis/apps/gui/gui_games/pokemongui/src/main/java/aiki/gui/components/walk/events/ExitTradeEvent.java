package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import aiki.network.stream.QuitAiki;
import code.gui.events.AbsActionListener;

public class ExitTradeEvent implements AbsActionListener {

    private WindowAiki window;

    public ExitTradeEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        QuitAiki quit_ = new QuitAiki();
        quit_.setClosing(false);
        quit_.setPlace(window.getIndexInGame());
        quit_.setLocale(window.getLanguageKey());
        window.sendObject(quit_);
    }
}
