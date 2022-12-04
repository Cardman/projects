package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.events.AbsActionListener;

public class PlayNextDealEvent implements AbsActionListener {

    private ContainerMulti container;

    public PlayNextDealEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.dealNext();
    }
}
