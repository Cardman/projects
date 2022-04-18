package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListener;

public class KeepPlayingRandomEvent implements AbsActionListener {

    private ContainerSingle container;

    public KeepPlayingRandomEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.keepPlayingRandom();
    }
}
