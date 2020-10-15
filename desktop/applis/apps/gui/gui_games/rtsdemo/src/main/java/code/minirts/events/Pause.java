package code.minirts.events;

import code.minirts.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pause implements ActionListener {

    private MainWindow window;

    public Pause(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.pause();
    }

}
