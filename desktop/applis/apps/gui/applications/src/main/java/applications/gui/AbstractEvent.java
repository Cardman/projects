package applications.gui;

import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public abstract class AbstractEvent implements AbsActionListener {
    private WindowApps window;
    private AbstractAtomicInteger lock;
    AbstractEvent(WindowApps _window, AbstractAtomicInteger _lock) {
        window = _window;
        lock = _lock;
    }

    @Override
    public void action() {
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
