package applications.gui;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.minirts.LaunchingDemo;
import code.minirts.WindowRts;

public final class DemoEvent extends AbstractEvent {

    DemoEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowRts.APPS_RTS, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingDemo l_;
        l_ = new LaunchingDemo(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
