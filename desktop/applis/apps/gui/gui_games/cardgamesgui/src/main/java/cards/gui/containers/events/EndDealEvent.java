package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListener;

public class EndDealEvent implements AbsActionListener {

    private ContainerSingle container;

    public EndDealEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.endDeal();
    }
}
