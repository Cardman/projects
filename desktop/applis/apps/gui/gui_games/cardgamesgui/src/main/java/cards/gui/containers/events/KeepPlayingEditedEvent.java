package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerSingle;

public class KeepPlayingEditedEvent extends MouseAdapter {

    private ContainerSingle container;

    public KeepPlayingEditedEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.keepPlayingEdited();
    }
}
