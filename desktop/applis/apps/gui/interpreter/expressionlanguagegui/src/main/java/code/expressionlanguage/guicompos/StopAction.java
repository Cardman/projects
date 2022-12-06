package code.expressionlanguage.guicompos;

import code.gui.events.AbsActionListener;

public final class StopAction implements AbsActionListener {
    private final WindowFull window;

    public StopAction(WindowFull _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.stopAction();
    }
}
