package minirts.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import minirts.MainWindow;

public class Animate implements ActionListener {

    private MainWindow window;

    public Animate(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.animate();
    }

}
