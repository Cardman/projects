package code.gui.events;

import code.gui.AbsQuit;

public class QuittingEvent implements AbsWindowListenerClosing {

    private final AbsQuit frame;

    public QuittingEvent(AbsQuit _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.quit();
    }
}
