package applications.gui;

import code.gui.AbsButton;
import code.player.gui.WindowPlayer;
import code.player.main.LaunchingPlayer;

public final class PlayerEvent extends AbstractEvent {
    PlayerEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowPlayer.APPS_MUSICPLAYER);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }
}
