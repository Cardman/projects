package cards.gui.containers.events;

import cards.gui.containers.*;
import code.gui.events.AbsActionListener;

public class ValidateDiscardEvent implements AbsActionListener {

    private final ContainerPlayableBelote container;

    public ValidateDiscardEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.validateDiscard();
    }
}
