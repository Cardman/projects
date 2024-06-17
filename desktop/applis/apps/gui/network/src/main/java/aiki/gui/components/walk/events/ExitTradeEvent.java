package aiki.gui.components.walk.events;

import aiki.network.stream.QuitAiki;
import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class ExitTradeEvent implements AbsActionListener {

    private final WindowNetWork window;

    public ExitTradeEvent(WindowNetWork _window) {
        window = _window;
    }

    @Override
    public void action() {
        QuitAiki quit_ = new QuitAiki();
        quit_.getContent().setClosing(false);
        quit_.setPlace(window.getIndexInGame());
        window.sendObject(quit_);
    }
}
