package code.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.gui.Clock;

public class UpdateTimeEvent implements ActionListener {

    private Clock clock;

    public UpdateTimeEvent(Clock _clock) {
        clock = _clock;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        clock.setTimeText();
    }

}
