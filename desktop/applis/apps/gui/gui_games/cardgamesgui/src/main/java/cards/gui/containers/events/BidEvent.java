package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerBelote;

public class BidEvent extends MouseAdapter {

    private ContainerBelote container;

    public BidEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.bid();
    }
}
