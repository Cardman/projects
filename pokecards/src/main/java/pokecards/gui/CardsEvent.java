package pokecards.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.GroupFrame;
import cards.main.LaunchingCards;

public class CardsEvent extends MouseAdapter {

    private MainWindow window;
    public CardsEvent(MainWindow _window) {
        window = _window;
    }
    @Override
    public final void mouseReleased(MouseEvent _e) {
        if (LaunchingCards.alreadyLaunched()) {
            return;
        }
        if (GroupFrame.tryToReopen(LaunchingCards.getMainWindowClass())) {
            LaunchingCards.increment();
            return;
        }
        String lg_ = window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards();
        l_.launch(lg_);
    }
}
