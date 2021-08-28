package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import aiki.network.stream.QuitAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
