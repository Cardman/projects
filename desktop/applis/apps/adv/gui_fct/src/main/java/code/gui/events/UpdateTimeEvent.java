package code.gui.events;

import code.gui.Clock;
import code.threads.AbstractThreadFactory;

public class UpdateTimeEvent implements Runnable {

    private final AbstractThreadFactory fact;
    private final Clock clock;

    public UpdateTimeEvent(AbstractThreadFactory _fact,Clock _clock) {
        fact = _fact;
        clock = _clock;
    }

    @Override
    public void run() {
        clock.setTimeText(fact);
    }

}
