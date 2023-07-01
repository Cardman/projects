package code.gui.events;

import code.gui.AbsOpenQuit;

public class QuittingEvent implements AbsWindowListenerClosing {

    private final AbsOpenQuit frame;

    public QuittingEvent(AbsOpenQuit _frame) {
        frame = _frame;
    }

    @Override
    public void windowClosing() {
        frame.quit();
    }
}
