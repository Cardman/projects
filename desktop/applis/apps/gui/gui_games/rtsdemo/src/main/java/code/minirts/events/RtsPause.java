package code.minirts.events;

import code.minirts.WindowRts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RtsPause implements ActionListener {

    private final WindowRts window;

    public RtsPause(WindowRts _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.pause();
    }

}
