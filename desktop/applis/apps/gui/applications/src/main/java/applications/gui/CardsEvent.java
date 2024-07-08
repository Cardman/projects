package applications.gui;

import cards.gui.WindowCards;
import cards.main.LaunchingCards;
import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;

public final class CardsEvent extends AbstractEvent {

    CardsEvent(WindowApps _window, AbsButton _but) {
        super(_window, _but);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowCards.APP_CARDS, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getFrames().getLanguage();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getWithAppFactories());
        l_.launch(lg_, getMainButton());
    }

}
