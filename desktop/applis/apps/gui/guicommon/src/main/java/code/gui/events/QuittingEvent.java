package code.gui.events;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import code.gui.GroupFrame;

public class QuittingEvent extends WindowAdapter {

    private GroupFrame frame;

    public QuittingEvent(GroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing(WindowEvent _event) {
        frame.quit();
    }
}
