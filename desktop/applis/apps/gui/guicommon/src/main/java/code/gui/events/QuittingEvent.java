package code.gui.events;

import code.gui.AbsGroupFrame;

public class QuittingEvent implements AbsWindowListenerClosing {

    private final AbsGroupFrame frame;

    public QuittingEvent(AbsGroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.quit();
    }
}
