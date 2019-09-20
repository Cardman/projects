package applications.gui;

import code.gui.GroupFrame;
import code.player.main.LaunchingPlayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class PlayerEvent extends MouseAdapter {
    private MainWindow window;
    PlayerEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingPlayer.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingPlayer.getMainWindowClass())) {
            LaunchingPlayer.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingPlayer l_;
        l_ = new LaunchingPlayer();
        l_.launch(lg_);
    }
}
