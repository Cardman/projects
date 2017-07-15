package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerPresident;

public class NoPlayPresidentEvent extends MouseAdapter {

    private ContainerPresident container;

    public NoPlayPresidentEvent(ContainerPresident _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.noPlay();
    }
}
