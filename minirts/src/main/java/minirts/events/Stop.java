package minirts.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import minirts.MainWindow;

public class Stop implements ActionListener {

    private MainWindow window;

    public Stop(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.stopGame();
    }

}
