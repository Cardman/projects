package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableBelote;
import code.gui.events.AbsActionListener;

public class BidEvent implements AbsActionListener {

    private final ContainerPlayableBelote container;

    public BidEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.bid();
    }
}
