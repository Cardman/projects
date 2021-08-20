package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ChooseModeEvent extends AbsMouseListenerRel {

    private WindowCards window;

    private boolean single;

    public ChooseModeEvent(WindowCards _window, boolean _single) {
        window = _window;
        single = _single;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        window.setSingle(single);
        if (single) {
            window.menuSoloGames();
        } else {
            window.menuMultiGames();
        }
    }
}
