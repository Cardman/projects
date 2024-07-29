package applications.gui;

import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;
import applications.code.network.LaunchingNetwork;
import code.network.WindowNetWork;

public final class NetWorkEvent extends AbstractEvent {

//    private final AbstractBaseExecutorService launcher;

    NetWorkEvent(WindowApps _window, AbsButton _b, LanguagesButtonsPair _p) {
        super(_window, _b, WindowNetWork.APPS_NETWORK, _p);
//        launcher = _window.getFrames().getThreadFactory().newExecutorService();
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingNetwork l_;
        l_ = new LaunchingNetwork(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }

//    public AbstractBaseExecutorService getLauncher() {
//        return launcher;
//    }
}
