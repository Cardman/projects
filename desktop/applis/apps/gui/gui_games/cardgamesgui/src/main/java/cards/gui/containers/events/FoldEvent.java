package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerBelote;

public class FoldEvent extends MouseAdapter {

    private ContainerBelote container;

    public FoldEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.fold();
    }
}
