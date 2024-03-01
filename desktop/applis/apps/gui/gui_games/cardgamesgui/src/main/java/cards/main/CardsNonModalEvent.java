package cards.main;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerSin;
import cards.gui.events.AbstractListenerCard;
import code.gui.events.AbsActionListenerAct;

public final class CardsNonModalEvent implements AbsActionListenerAct {
    private final WindowCards window;
    private ContainerSin containerSingle;

    public CardsNonModalEvent(ContainerSin _c) {
        this(_c.window());
        containerSingle = _c;
    }

    public CardsNonModalEvent(WindowCards _w) {
        this.window = _w;
    }

    @Override
    public boolean act() {
        return AbstractListenerCard.aliveEvents(containerSingle, window);
//        if (containerSingle != null) {
//            return AbstractListenerCard.aliveEvents(containerSingle, window);
//        }
//        return !window.getModal().get();
    }
}
