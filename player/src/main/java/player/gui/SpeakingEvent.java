package player.gui;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SpeakingEvent implements LineListener {

    private MainWindow window;

    public SpeakingEvent(MainWindow _window) {
        window = _window;
    }

    @Override
    public void update(LineEvent _event) {
        window.updateClip(_event);
    }

}
