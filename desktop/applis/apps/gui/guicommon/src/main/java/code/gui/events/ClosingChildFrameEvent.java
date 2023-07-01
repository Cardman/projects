package code.gui.events;

import code.gui.AbsChildFrame;

public class ClosingChildFrameEvent implements AbsWindowListenerClosing {

    private final AbsChildFrame frame;

    public ClosingChildFrameEvent(AbsChildFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.closeWindow();
    }
}
