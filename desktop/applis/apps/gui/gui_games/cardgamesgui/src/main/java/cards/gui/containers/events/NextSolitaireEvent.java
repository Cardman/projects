package cards.gui.containers.events;

import cards.gui.containers.*;
import code.gui.events.*;

public final class NextSolitaireEvent implements AbsActionListener {
    private final ContainerSolitaire container;

    public NextSolitaireEvent(ContainerSolitaire _c) {
        this.container = _c;
    }

    @Override
    public void action() {
        container.modify();
    }

}
