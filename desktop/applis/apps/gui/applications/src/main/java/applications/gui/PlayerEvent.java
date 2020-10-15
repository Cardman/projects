package applications.gui;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.player.main.LaunchingPlayer;

import java.util.concurrent.atomic.AtomicInteger;

public final class PlayerEvent extends AbstractEvent {
    PlayerEvent(MainWindow _window, AtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingPlayer.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer(_window.getFrames());
        l_.launch(lg_);
    }
}
