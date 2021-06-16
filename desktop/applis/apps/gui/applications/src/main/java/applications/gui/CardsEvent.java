package applications.gui;

import cards.main.CardFactories;
import cards.main.LaunchingCards;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public final class CardsEvent extends AbstractEvent {

    private final CardFactories cardFactories;
    CardsEvent(MainWindow _window, AbstractAtomicInteger _at, CardFactories _cardFactories) {
        super(_window,_at);
        cardFactories = _cardFactories;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GroupFrame.tryToReopen(LaunchingCards.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getFrames(),cardFactories);
        l_.launch(lg_);
    }

}
