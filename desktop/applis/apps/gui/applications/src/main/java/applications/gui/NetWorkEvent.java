package applications.gui;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.network.LaunchingNetwork;
import code.network.WindowNetWork;
import code.threads.AbstractBaseExecutorService;

public final class NetWorkEvent extends AbstractEvent {

    private final AbstractBaseExecutorService launcher;

    NetWorkEvent(WindowApps _window, AbsButton _b) {
        super(_window, _b);
        launcher = _window.getFrames().getThreadFactory().newExecutorService();
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowNetWork.APPS_NETWORK, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingNetwork l_;
        l_ = new LaunchingNetwork(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }

    public AbstractBaseExecutorService getLauncher() {
        return launcher;
    }
}
