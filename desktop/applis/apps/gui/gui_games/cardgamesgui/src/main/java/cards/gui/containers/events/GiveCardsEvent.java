package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayablePresident;
import code.gui.events.AbsActionListener;

public class GiveCardsEvent implements AbsActionListener {

    private ContainerPlayablePresident container;

    public GiveCardsEvent(ContainerPlayablePresident _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.discard();
    }
}
