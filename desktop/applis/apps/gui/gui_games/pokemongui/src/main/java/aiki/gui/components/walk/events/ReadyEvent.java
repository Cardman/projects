package aiki.gui.components.walk.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import aiki.gui.MainWindow;
import aiki.network.stream.Ready;
import code.gui.CustCheckBox;

public class ReadyEvent implements ActionListener {

    private MainWindow window;

    private CustCheckBox ready;

    public ReadyEvent(MainWindow _window, CustCheckBox _ready) {
        window = _window;
        ready = _ready;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        Ready choice_ = new Ready();
        choice_.setIndex(window.getIndexInGame());
        choice_.setReady(ready.isSelected());
        window.sendObject(choice_);
    }
}
