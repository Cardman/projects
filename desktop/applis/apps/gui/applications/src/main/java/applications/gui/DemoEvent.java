package applications.gui;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.minirts.LaunchingDemo;

import java.util.concurrent.atomic.AtomicInteger;

public final class DemoEvent extends AbstractEvent {

    DemoEvent(MainWindow _window, AtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingDemo.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingDemo l_;
        l_ = new LaunchingDemo(_window.getFrames());
        l_.launch(lg_);
    }
}
