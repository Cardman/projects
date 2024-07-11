package applications.gui;

import cards.gui.WindowCards;
import cards.main.LaunchingCards;
import code.gui.AbsButton;

public final class CardsEvent extends AbstractEvent {

    CardsEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but, WindowCards.APP_CARDS);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }

}
