package aiki.gui.events;

import aiki.gui.WindowAiki;
import code.gui.events.AbsActionListener;

public class ManageDifficultyEvent implements AbsActionListener {

    private WindowAiki window;

    public ManageDifficultyEvent(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.manageDifficulty();
    }

}
