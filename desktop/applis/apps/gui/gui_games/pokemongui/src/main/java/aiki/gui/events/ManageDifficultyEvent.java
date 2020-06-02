package aiki.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.MainWindow;

public class ManageDifficultyEvent implements ActionListener {

    private MainWindow window;

    public ManageDifficultyEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.manageDifficulty();
    }

}
