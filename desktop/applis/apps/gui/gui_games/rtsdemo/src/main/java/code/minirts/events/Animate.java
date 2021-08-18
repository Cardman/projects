package code.minirts.events;

import code.minirts.WindowRts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animate implements ActionListener {

    private WindowRts window;

    public Animate(WindowRts _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.animate();
    }

}
