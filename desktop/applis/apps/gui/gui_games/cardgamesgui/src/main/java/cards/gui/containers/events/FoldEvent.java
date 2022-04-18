package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerPlayableBelote;
import code.gui.events.AbsActionListener;

public class FoldEvent implements AbsActionListener {

    private ContainerPlayableBelote container;

    public FoldEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.fold();
    }
}
