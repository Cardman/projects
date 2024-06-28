package code.converterimages.gui;
import code.gui.events.AbsActionListener;

public final class ExportEvent implements AbsActionListener {

    private final WindowConverter window;

    public ExportEvent(WindowConverter _window) {
        window = _window;
    }

    @Override
    public void action() {
        window.export();
    }
}
