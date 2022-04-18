package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableTarot;
import code.gui.events.AbsActionListener;

public class ValidateDogEvent implements AbsActionListener {

    private ContainerPlayableTarot container;

    public ValidateDogEvent(ContainerPlayableTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.validateDog();
    }
}
