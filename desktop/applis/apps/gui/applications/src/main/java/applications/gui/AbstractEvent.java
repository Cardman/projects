package applications.gui;

import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public abstract class AbstractEvent extends AbsMouseListenerRel {
    private WindowApps window;
    private AbstractAtomicInteger lock;
    AbstractEvent(WindowApps _window, AbstractAtomicInteger _lock) {
        window = _window;
        lock = _lock;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
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
