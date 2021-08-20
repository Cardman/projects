package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ManageParamsEvent implements AbsActionListener {

    private WindowAiki window;

    public ManageParamsEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageParams();
    }

}
