package applications.gui;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.player.gui.WindowPlayer;
import code.player.main.LaunchingPlayer;

public final class PlayerEvent extends AbstractEvent {
    PlayerEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowPlayer.APPS_MUSICPLAYER, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
