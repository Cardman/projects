package code.expressionlanguage.guicompos;

import code.gui.events.AbsActionListener;

public final class CoverageAction implements AbsActionListener {
    private final WindowFull window;

    public CoverageAction(WindowFull _w) {
        this.window = _w;
    }

    @Override
    public void action() {
        window.coverage();
    }
}
