package applications.gui;

import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;
import applications.code.renders.LaunchingRenders;
import code.renders.MessagesRenders;

public final class RenderEvent extends AbstractEvent {

    RenderEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesRenders.APPS_RENDERS_SITES, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
