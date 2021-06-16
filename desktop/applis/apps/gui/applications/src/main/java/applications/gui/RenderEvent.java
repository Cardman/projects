package applications.gui;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.renders.LaunchingRenders;
import code.threads.AbstractAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public final class RenderEvent extends AbstractEvent {

    RenderEvent(MainWindow _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingRenders.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(_window.getFrames());
        l_.launch(lg_);
    }
}
