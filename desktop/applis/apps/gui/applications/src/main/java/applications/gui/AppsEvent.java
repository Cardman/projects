package applications.gui;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.FrameUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class AppsEvent extends AbstractEvent {

    AppsEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return FrameUtil.tryToReopen(LaunchingFull.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingFull l_;
        l_ = new LaunchingFull(_window.getFrames());
        l_.launch(lg_);
    }
}
