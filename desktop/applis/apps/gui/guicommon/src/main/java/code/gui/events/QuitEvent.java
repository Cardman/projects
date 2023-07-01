package code.gui.events;

import code.gui.AbsOpenQuit;

public class QuitEvent implements AbsActionListener {

    private final AbsOpenQuit frame;

    public QuitEvent(AbsOpenQuit _frame) {
        frame = _frame;
    }

    @Override
    public void action() {
        frame.quit();
    }
}
