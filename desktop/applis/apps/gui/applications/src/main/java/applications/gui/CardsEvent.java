package applications.gui;

import cards.main.LaunchingCards;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.util.concurrent.atomic.AtomicInteger;

public final class CardsEvent extends AbstractEvent {

    CardsEvent(MainWindow _window, AtomicInteger at_) {
        super(_window,at_);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingCards.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getFrames());
        l_.launch(lg_);
    }

}
