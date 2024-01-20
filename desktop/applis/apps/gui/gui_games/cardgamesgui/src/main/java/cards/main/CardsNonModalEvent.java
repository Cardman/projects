package cards.main;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListenerAct;

public final class CardsNonModalEvent implements AbsActionListenerAct {
    private final WindowCards window;

    public CardsNonModalEvent(ContainerSingle _c) {
        this(_c.window());
    }

    public CardsNonModalEvent(WindowCards _w) {
        this.window = _w;
    }

    @Override
    public boolean act() {
        return !window.getFileSaveFrame().getFrame().isVisible();
    }
}
