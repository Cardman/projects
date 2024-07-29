package applications.gui;

import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;
import applications.code.minirts.LaunchingDemo;
import code.minirts.WindowRts;

public final class DemoEvent extends AbstractEvent {

    DemoEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, WindowRts.APPS_RTS, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingDemo l_;
        l_ = new LaunchingDemo(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
