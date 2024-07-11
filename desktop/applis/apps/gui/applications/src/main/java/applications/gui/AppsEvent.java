package applications.gui;

import code.expressionlanguage.guicompos.LaunchingFull;
import code.expressionlanguage.guicompos.WindowFull;
import code.gui.AbsButton;

public final class AppsEvent extends AbstractEvent {

    AppsEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowFull.APPS_LAUNCHER);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingFull l_;
        l_ = new LaunchingFull(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
