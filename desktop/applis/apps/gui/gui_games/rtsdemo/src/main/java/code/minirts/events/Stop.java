package code.minirts.events;

import code.minirts.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stop implements ActionListener {

    private MainWindow window;

    public Stop(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.stopGame();
    }

}
