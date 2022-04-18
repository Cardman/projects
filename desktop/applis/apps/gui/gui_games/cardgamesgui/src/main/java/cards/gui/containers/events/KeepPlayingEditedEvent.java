package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListener;

public class KeepPlayingEditedEvent  implements AbsActionListener {

    private ContainerSingle container;

    public KeepPlayingEditedEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.keepPlayingEdited();
    }
}
