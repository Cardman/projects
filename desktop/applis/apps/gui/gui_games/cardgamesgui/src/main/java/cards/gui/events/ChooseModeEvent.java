package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ChooseModeEvent implements AbsActionListener {

    private WindowCards window;

    private boolean single;

    public ChooseModeEvent(WindowCards _window, boolean _single) {
        window = _window;
        single = _single;
    }

    @Override
    public void action() {
        window.setSingle(single);
        if (single) {
            window.menuSoloGames();
        } else {
            window.menuMultiGames();
        }
    }
}
