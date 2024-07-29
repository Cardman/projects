package applications.gui;

import code.expressionlanguage.guicompos.WindowFull;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;
import applications.code.player.main.LaunchRecord;

public final class RecordEvent extends AbstractEvent {

    RecordEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, WindowFull.APPS_LAUNCHER, _p);
    }
    public static RecordEvent rec(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        return new RecordEvent(_window, _but, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchRecord l_;
        l_ = new LaunchRecord(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
