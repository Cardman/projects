package applications.gui;

import cards.main.LaunchingCards;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicInteger;

public final class CardsEvent extends AbstractEvent {

    CardsEvent(WindowApps _window, AbstractAtomicInteger _at) {
        super(_window,_at);
    }

    @Override
    protected boolean tryToReopen(AbstractProgramInfos _list) {
        return GuiBaseUtil.tryToReopen(LaunchingCards.getMainWindowClass(), _list);
    }

    @Override
    protected void launch(WindowApps _window) {
        String lg_ = _window.getLanguageKey();
        LaunchingCards l_;
        l_ = new LaunchingCards(_window.getFrames());
        l_.launch(lg_);
    }

}
