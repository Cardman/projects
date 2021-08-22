package aiki.gui.components.walk.events;

import aiki.gui.WindowAiki;
import aiki.network.stream.QuitAiki;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ExitTradeEvent extends AbsMouseListenerRel {

    private WindowAiki window;

    public ExitTradeEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        QuitAiki quit_ = new QuitAiki();
        quit_.setClosing(false);
        quit_.setPlace(window.getIndexInGame());
        quit_.setLocale(window.getLanguageKey());
        window.sendObject(quit_);
    }
}
