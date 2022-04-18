package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.events.AbsActionListener;

public class BidEvent implements AbsActionListener {

    private ContainerBelote container;

    public BidEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.bid();
    }
}
