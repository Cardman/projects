package applications.gui;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.gui.FrameUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.main.LaunchRecord;
import code.threads.AbstractAtomicInteger;

public final class RecordEvent extends AbstractEvent {

    RecordEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return FrameUtil.tryToReopen(LaunchingFull.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchRecord l_;
        l_ = new LaunchRecord(_window.getFrames());
        l_.launch(lg_);
    }
}
