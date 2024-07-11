package applications.gui;

import code.expressionlanguage.guicompos.WindowFull;
import code.gui.AbsButton;
import code.player.main.LaunchRecord;

public final class RecordEvent extends AbstractEvent {

    RecordEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowFull.APPS_LAUNCHER);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchRecord l_;
        l_ = new LaunchRecord(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
