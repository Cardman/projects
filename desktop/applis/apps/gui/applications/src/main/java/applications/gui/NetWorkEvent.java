package applications.gui;

import code.gui.AbsButton;
import code.network.LaunchingNetwork;
import code.network.WindowNetWork;
import code.threads.AbstractBaseExecutorService;

public final class NetWorkEvent extends AbstractEvent {

    private final AbstractBaseExecutorService launcher;

    NetWorkEvent(WindowApps _window, AbsButton _b) {
        super(_window, _b, WindowNetWork.APPS_NETWORK);
        launcher = _window.getFrames().getThreadFactory().newExecutorService();
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
