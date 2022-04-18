package cards.gui.containers.events;

import cards.gui.containers.ContainerPresident;
import code.gui.events.AbsActionListener;

public class GiveCardsEvent implements AbsActionListener {

    private ContainerPresident container;

    public GiveCardsEvent(ContainerPresident _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.discard();
    }
}
