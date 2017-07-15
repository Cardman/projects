package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerTarot;

public class TakeDogEvent extends MouseAdapter {

    private ContainerTarot container;

    public TakeDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.prendreCartesChien();
    }
}
