package applications.gui;

import cards.gui.WindowCards;
import cards.main.LaunchingCards;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class CardsEvent extends AbstractEvent {

    private final AppFactories cardFactories;
    CardsEvent(WindowApps _window, AbstractAtomicInteger _at, AppFactories _cf) {
        super(_window,_at);
        cardFactories = _cf;
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(WindowCards.APP_CARDS, _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getFrames(),cardFactories);
        l_.launch(lg_);
    }

}
