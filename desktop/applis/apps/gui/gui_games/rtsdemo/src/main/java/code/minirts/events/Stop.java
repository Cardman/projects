package code.minirts.events;

import code.minirts.WindowRts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stop implements ActionListener {

    private WindowRts window;

    public Stop(WindowRts _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.stopGame();
    }

}
