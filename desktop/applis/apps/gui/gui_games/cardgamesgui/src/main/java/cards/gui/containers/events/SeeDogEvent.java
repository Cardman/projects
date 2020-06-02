package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerSingleTarot;

public class SeeDogEvent extends MouseAdapter {

    private ContainerSingleTarot container;

    public SeeDogEvent(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.voirChien();
    }
}
