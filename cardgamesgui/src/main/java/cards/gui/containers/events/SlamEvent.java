package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerTarot;

public class SlamEvent extends MouseAdapter {

    private ContainerTarot container;

    public SlamEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.annonceTarotChelem();
    }
}
