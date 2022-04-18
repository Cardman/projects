package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerPlayableBelote;
import code.gui.events.AbsActionListener;

public class BidEvent implements AbsActionListener {

    private ContainerPlayableBelote container;

    public BidEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.bid();
    }
}
