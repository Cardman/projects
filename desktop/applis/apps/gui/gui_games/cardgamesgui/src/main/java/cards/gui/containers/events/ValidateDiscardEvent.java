package cards.gui.containers.events;

import cards.gui.containers.ContainerSingleBelote;
import code.gui.events.AbsActionListener;

public class ValidateDiscardEvent implements AbsActionListener {

    private ContainerSingleBelote container;

    public ValidateDiscardEvent(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.validateDiscard();
    }
}
