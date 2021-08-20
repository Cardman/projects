package code.gui.events;

import code.gui.GroupFrame;

public class QuitEvent implements AbsActionListener {

    private final GroupFrame frame;

    public QuitEvent(GroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void action() {
        frame.quit();
    }
}
