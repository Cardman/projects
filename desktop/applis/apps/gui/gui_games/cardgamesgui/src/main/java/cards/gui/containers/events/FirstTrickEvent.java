package cards.gui.containers.events;

import cards.gui.containers.ContainerSinglePausable;
import code.gui.events.AbsActionListener;

public final class FirstTrickEvent<T> implements AbsActionListener {

    private final ContainerSinglePausable<T> container;

    public FirstTrickEvent(ContainerSinglePausable<T> _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.firstTrick();
    }
}
