package cards.gui.containers.events;

import cards.gui.containers.ContainerSin;
import code.gui.events.AbsActionListener;

public class KeepPlayingRandomEvent implements AbsActionListener {

    private final ContainerSin container;

    public KeepPlayingRandomEvent(ContainerSin _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.keepPlayingRandom();
    }
}
