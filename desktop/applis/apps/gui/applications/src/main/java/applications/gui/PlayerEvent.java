package applications.gui;

import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.gui.WindowPlayer;
import code.player.main.LaunchingPlayer;
import code.threads.AbstractAtomicInteger;

public final class PlayerEvent extends AbstractEvent {
    PlayerEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowPlayer.APPS_MUSICPLAYER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getWithAppFactories());
        l_.launch(lg_);
    }
}
