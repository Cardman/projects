package applications.gui;

import cards.main.LaunchingCards;
import code.gui.GroupFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class CardsEvent extends MouseAdapter {

    private MainWindow window;
    CardsEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public void mouseReleased(MouseEvent _e) {
        if (LaunchingCards.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingCards.getMainWindowClass(), window.getFrames())) {
            LaunchingCards.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards();
        l_.launch(lg_, window.getFrames());
    }
}
