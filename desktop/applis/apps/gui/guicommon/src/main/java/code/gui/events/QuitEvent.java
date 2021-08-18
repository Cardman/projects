package code.gui.events;

import code.gui.GroupFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitEvent implements ActionListener {

    private final GroupFrame frame;

    public QuitEvent(GroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        frame.quit();
    }
}
