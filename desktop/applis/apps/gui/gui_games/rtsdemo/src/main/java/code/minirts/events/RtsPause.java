package code.minirts.events;

import code.minirts.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RtsPause implements ActionListener {

    private final MainWindow window;

    public RtsPause(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.pause();
    }

}
