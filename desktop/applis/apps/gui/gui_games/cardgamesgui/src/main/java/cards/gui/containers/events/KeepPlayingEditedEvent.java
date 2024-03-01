package cards.gui.containers.events;

import cards.gui.containers.ContainerSin;
import code.gui.events.AbsActionListener;

public class KeepPlayingEditedEvent  implements AbsActionListener {

    private final ContainerSin container;

    public KeepPlayingEditedEvent(ContainerSin _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.keepPlayingEdited();
    }
}
