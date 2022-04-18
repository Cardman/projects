package cards.gui.events;

import cards.gui.WindowCards;
import code.gui.events.AbsActionListener;

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
