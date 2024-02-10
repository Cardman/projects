package cards.gui.containers.events;

import cards.gui.containers.ContainerSinglePausable;
import code.gui.events.AbsActionListener;

public class NextTrickEvent implements AbsActionListener {

    private final ContainerSinglePausable container;

    public NextTrickEvent(ContainerSinglePausable _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.nextTrick();
    }
}
