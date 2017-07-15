package cards.gui.containers.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerTarot;

public class ValidateDogEvent extends MouseAdapter {

    private ContainerTarot container;

    public ValidateDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.validateDog();
    }
}
