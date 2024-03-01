package cards.gui.containers.events;

import cards.gui.containers.ContainerSinglePausable;
import code.gui.events.AbsActionListener;

public class EndDealEvent<T> implements AbsActionListener {

    private final ContainerSinglePausable<T> container;

    public EndDealEvent(ContainerSinglePausable<T> _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.endDeal();
    }
}
