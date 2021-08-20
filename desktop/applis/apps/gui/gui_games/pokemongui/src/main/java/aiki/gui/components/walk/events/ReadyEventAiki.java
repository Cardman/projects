package aiki.gui.components.walk.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;
import aiki.network.stream.ReadyAiki;
import code.gui.AbsActionListener;
import code.gui.CustCheckBox;

public class ReadyEventAiki implements AbsActionListener {

    private WindowAiki window;

    private CustCheckBox ready;

    public ReadyEventAiki(WindowAiki _window, CustCheckBox _ready) {
        window = _window;
        ready = _ready;
    }

    @Override
    public void action() {
        ReadyAiki choice_ = new ReadyAiki();
        choice_.setIndex(window.getIndexInGame());
        choice_.setReady(ready.isSelected());
        window.sendObject(choice_);
    }
}
