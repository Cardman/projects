package applications.gui;

import code.gui.AbsButton;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.network.LaunchingNetwork;
import code.network.WindowNetWork;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractBaseExecutorService;

public final class NetWorkEvent extends AbstractEvent {

    private final AbsButton button;
    private final AbstractBaseExecutorService launcher;
    private final AppFactories appFactories;

    NetWorkEvent(WindowApps _window, AbstractAtomicInteger _at, AbsButton _b, AppFactories _cdmFactory) {
        super(_window,_at);
        button = _b;
        appFactories = _cdmFactory;
        launcher = _window.getFrames().getThreadFactory().newExecutorService();
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowNetWork.APPS_NETWORK, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingNetwork l_;
        l_ = new LaunchingNetwork(_window.getFrames(), button, launcher,appFactories);
        l_.launch(lg_);
    }

    public AbstractBaseExecutorService getLauncher() {
        return launcher;
    }
}
