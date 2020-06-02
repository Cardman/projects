package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerMulti;

public class PlayFirstDealEvent extends MouseAdapter {

    private ContainerMulti container;

    public PlayFirstDealEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.dealFirst();
    }
}
