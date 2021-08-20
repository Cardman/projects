package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.WindowAiki;
import code.gui.AbsActionListener;

public class ShowDataFightEvent implements AbsActionListener {

    private WindowAiki window;

    public ShowDataFightEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.showFightData();
    }

}
