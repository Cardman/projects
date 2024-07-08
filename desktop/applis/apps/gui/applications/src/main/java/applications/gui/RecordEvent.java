package applications.gui;

import code.expressionlanguage.guicompos.WindowFull;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.main.LaunchRecord;

public final class RecordEvent extends AbstractEvent {

    RecordEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
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
        l_.launch(lg_, getMainButton());
    }
}
