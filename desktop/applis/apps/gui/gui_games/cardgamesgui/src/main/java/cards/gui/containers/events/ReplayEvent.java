package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListener;

public class ReplayEvent implements AbsActionListener {

    private ContainerSingle container;

    public ReplayEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.replay();
    }
}
