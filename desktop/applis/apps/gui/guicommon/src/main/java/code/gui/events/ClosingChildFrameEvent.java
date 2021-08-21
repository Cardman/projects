package code.gui.events;

import code.gui.ChildFrame;

public class ClosingChildFrameEvent extends AbsWindowListenerClosing {

    private ChildFrame frame;

    public ClosingChildFrameEvent(ChildFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.closeWindow();
    }
}
