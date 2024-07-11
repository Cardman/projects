package applications.gui;

import code.gui.AbsButton;
import code.renders.LaunchingRenders;
import code.renders.WindowRenders;

public final class RenderEvent extends AbstractEvent {

    RenderEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowRenders.APPS_RENDERS_SITES);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
