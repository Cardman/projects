package applications.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractEvent extends MouseAdapter {
    private WindowApps window;
    private AbstractAtomicInteger lock;
    AbstractEvent(WindowApps _window, AbstractAtomicInteger _lock) {
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
    protected abstract void launch(WindowApps _window);
    public AbstractAtomicInteger getLock() {
        return lock;
    }
}
