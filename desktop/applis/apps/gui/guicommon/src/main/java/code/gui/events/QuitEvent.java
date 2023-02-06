package code.gui.events;

import code.gui.AbsGroupFrame;

public class QuitEvent implements AbsActionListener {

    private final AbsGroupFrame frame;

    public QuitEvent(AbsGroupFrame _frame) {
        frame = _frame;
    }

    @Override
    public void action() {
        frame.quit();
    }
}
