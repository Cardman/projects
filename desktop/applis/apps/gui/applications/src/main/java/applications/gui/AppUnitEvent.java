package applications.gui;

import code.expressionlanguage.gui.unit.LaunchingAppUnitTests;
import code.gui.FrameUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class AppUnitEvent extends AbstractEvent {

    AppUnitEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return FrameUtil.tryToReopen(LaunchingAppUnitTests.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingAppUnitTests l_;
        l_ = new LaunchingAppUnitTests(_window.getFrames());
        l_.launch(lg_);
    }
}
