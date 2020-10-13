package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.util.concurrent.atomic.AtomicInteger;

public final class AppUnitEvent extends AbstractEvent {

    AppUnitEvent(MainWindow _window, AtomicInteger at_) {
        super(_window,at_);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingAppUnitTests.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getFrames());
        l_.launch(lg_);
    }
}
