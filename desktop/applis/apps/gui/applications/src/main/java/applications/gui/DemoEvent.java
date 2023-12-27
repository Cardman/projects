package applications.gui;

import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.minirts.LaunchingDemo;
import code.minirts.WindowRts;
import code.threads.AbstractAtomicInteger;

public final class DemoEvent extends AbstractEvent {
    private final AppFactories appFactories;

    DemoEvent(WindowApps _window, AbstractAtomicInteger _at, AppFactories _cdmFactory) {
        super(_window,_at);
        appFactories = _cdmFactory;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowRts.APPS_RTS, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingDemo l_;
        l_ = new LaunchingDemo(_window.getFrames(),appFactories);
        l_.launch(lg_);
    }
}
