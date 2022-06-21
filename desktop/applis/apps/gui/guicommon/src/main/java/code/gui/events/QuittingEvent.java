package code.gui.events;

import code.gui.GroupFrame;

public class QuittingEvent implements AbsWindowListenerClosing {

    private final GroupFrame frame;

    public QuittingEvent(GroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.quit();
    }
}
