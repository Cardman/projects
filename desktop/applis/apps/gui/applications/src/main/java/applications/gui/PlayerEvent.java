package applications.gui;

import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;
import code.player.gui.MessagesSongs;
import applications.code.player.main.LaunchingPlayer;

public final class PlayerEvent extends AbstractEvent {
    PlayerEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesSongs.APPS_MUSICPLAYER, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }
}
