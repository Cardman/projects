package player.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTimeEvent implements ActionListener {

    private MainWindow window;

    public UpdateTimeEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        window.setElapsedTime();
    }

}
