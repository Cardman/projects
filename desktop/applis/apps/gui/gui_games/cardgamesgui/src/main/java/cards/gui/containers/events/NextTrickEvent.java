package cards.gui.containers.events;

import cards.gui.containers.ContainerSinglePausable;
import code.gui.events.AbsActionListener;

public class NextTrickEvent<T> implements AbsActionListener {

    private final ContainerSinglePausable<T> container;

    public NextTrickEvent(ContainerSinglePausable<T> _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.nextTrick();
    }
}
