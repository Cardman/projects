package aiki.gui.components.walk.events;

import code.gui.events.AbsActionListener;
import code.network.WindowNetWork;

public class ReadyEventAiki implements AbsActionListener {

    private final WindowNetWork window;

    public ReadyEventAiki(WindowNetWork _window) {
        window = _window;
    }

    @Override
    public void action() {
//        ReadyAiki choice_ = new ReadyAiki();
//        choice_.setIndex(window.getIndexInGame());
//        choice_.setReady(ready.isSelected());
        window.sendReady();
    }
}
