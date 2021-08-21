package code.gui.events;

import code.gui.GroupFrame;

public class QuittingEvent extends AbsWindowListenerClosing {

    private GroupFrame frame;

    public QuittingEvent(GroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.quit();
    }
}
