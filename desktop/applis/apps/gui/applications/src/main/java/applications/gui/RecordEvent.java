package applications.gui;

import code.expressionlanguage.guicompos.WindowFull;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.main.LaunchRecord;
import code.threads.AbstractAtomicInteger;

public final class RecordEvent extends AbstractEvent {

    RecordEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowFull.APPS_LAUNCHER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchRecord l_;
        l_ = new LaunchRecord(_window.getWithAppFactories());
        l_.launch(lg_);
    }
}
