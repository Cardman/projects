package applications.gui;

import code.gui.initialize.AbstractProgramInfos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEvent extends MouseAdapter {
    private MainWindow window;
    private AtomicInteger lock;
    AbstractEvent(MainWindow _window, AtomicInteger _lock) {
        window = _window;
        lock = _lock;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (getLock().get() > 0) {
            return;
        }
        if (tryToReopen(window.getFrames())) {
            getLock().incrementAndGet();
            return;
        }
        launch(window);
    }
    protected abstract boolean tryToReopen(AbstractProgramInfos _list);
    protected abstract void launch(MainWindow _window);
    public AtomicInteger getLock() {
        return lock;
    }
}
