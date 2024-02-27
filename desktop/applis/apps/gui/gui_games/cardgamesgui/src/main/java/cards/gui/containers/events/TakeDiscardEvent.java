package cards.gui.containers.events;

import cards.gui.containers.ContainerSingleBelote;
import code.gui.events.AbsActionListener;

public class TakeDiscardEvent implements AbsActionListener {

    private final ContainerSingleBelote container;

    public TakeDiscardEvent(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.prendreCartesChien();
    }
}
