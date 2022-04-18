package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.events.AbsActionListener;

public class ValidateDogEvent implements AbsActionListener {

    private ContainerTarot container;

    public ValidateDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.validateDog();
    }
}
