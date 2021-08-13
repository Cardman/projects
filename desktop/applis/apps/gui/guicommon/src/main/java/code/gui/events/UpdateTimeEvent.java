package code.gui.events;

import code.gui.Clock;

public class UpdateTimeEvent implements Runnable {

    private final Clock clock;

    public UpdateTimeEvent(Clock _clock) {
        clock = _clock;
    }

    @Override
    public void run() {
        clock.setTimeText();
    }

}
