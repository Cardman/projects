package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.events.AbsActionListener;

public class ChangeBeloteDeclareEvent implements AbsActionListener {

    private ContainerBelote container;

    public ChangeBeloteDeclareEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.invertBeloteDeclare();
    }
}
