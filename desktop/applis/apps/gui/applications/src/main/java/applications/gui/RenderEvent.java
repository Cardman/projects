package applications.gui;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.renders.LaunchingRenders;
import code.renders.WindowRenders;

public final class RenderEvent extends AbstractEvent {

    RenderEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowRenders.APPS_RENDERS_SITES, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingRenders l_;
        l_ = new LaunchingRenders(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
