package applications.gui;

import cards.facade.MessagesCardGames;
import applications.main.LaunchingCards;
import code.gui.AbsButton;
import code.gui.LanguagesButtonsPair;

public final class CardsEvent extends AbstractEvent {

    CardsEvent(WindowApps _window, AbsButton _but, LanguagesButtonsPair _p) {
        super(_window, _but, MessagesCardGames.CARDS, _p);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton(), getPair());
    }

}
