package code.gui.events;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import code.gui.ChildFrame;

public class ClosingChildFrameEvent extends WindowAdapter {

    private ChildFrame frame;

    public ClosingChildFrameEvent(ChildFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing(WindowEvent _arg0) {
        frame.closeWindow();
    }
}
