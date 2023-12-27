package applications.gui;

import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.gui.WindowPlayer;
import code.player.main.LaunchingPlayer;
import code.threads.AbstractAtomicInteger;

public final class PlayerEvent extends AbstractEvent {
    private final AppFactories appFactories;
    PlayerEvent(WindowApps _window, AbstractAtomicInteger _at, AppFactories _cdmFactory) {
        super(_window,_at);
        appFactories = _cdmFactory;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowPlayer.APPS_MUSICPLAYER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getFrames(),appFactories);
        l_.launch(lg_);
    }
}
