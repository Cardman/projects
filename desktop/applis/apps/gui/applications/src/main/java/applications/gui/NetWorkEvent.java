package applications.gui;

import aiki.main.AikiFactory;
import cards.main.CardFactories;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.network.LaunchingNetwork;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractBaseExecutorService;

public final class NetWorkEvent extends AbstractEvent {

    private final CardFactories cardFactories;
    private final AikiFactory aikiFactory;
    private final AbsButton button;
    private final AbstractBaseExecutorService launcher;

    NetWorkEvent(WindowApps _window, AbstractAtomicInteger _at, CardFactories _cardFactories, AikiFactory _aikiFactory, AbsButton _b) {
        super(_window,_at);
        cardFactories = _cardFactories;
        aikiFactory = _aikiFactory;
        button = _b;
        launcher = _window.getFrames().getThreadFactory().newExecutorService();
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(LaunchingNetwork.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingNetwork l_;
        l_ = new LaunchingNetwork(_window.getFrames(), cardFactories, aikiFactory, button, launcher);
        l_.launch(lg_);
    }

    public AbstractBaseExecutorService getLauncher() {
        return launcher;
    }
}
