package code.gui.events;

import code.gui.ChildFrame;

public class ClosingChildFrameEvent implements AbsWindowListenerClosing {

    private final ChildFrame frame;

    public ClosingChildFrameEvent(ChildFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.closeWindow();
    }
}
