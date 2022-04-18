package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.events.AbsActionListener;

public class FoldEvent implements AbsActionListener {

    private ContainerBelote container;

    public FoldEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.fold();
    }
}
